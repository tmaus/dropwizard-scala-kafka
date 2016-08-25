package com.pirack

import io.dropwizard.lifecycle.Managed
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory
import scala.collection.JavaConversions._

/**
  * Created by tmaus on 18/8/16.
  */

trait KafkaConsumerCallback[A,B] {
  def process(k:A, v:B)
}

object KafkaManager {

}

class KafkaManager[A,B](configuration:MyConfiguration, callback:KafkaConsumerCallback[A,B], topic:String) extends Managed{
  @volatile var done:Boolean = false
  val LOG = LoggerFactory.getLogger(KafkaManager.getClass)

  override def stop(): Unit = {
    done = true
  }

  override def start(): Unit = {
    new Thread(new Runnable {
      override def run(): Unit = {
        val consumer = new KafkaConsumer[A, B](configuration.kafkaConfiguration.getConsumerProps())
        consumer.subscribe(List(topic))
        LOG.info("registered kafka consumer for topic '{}'", topic)
        while(!done){
          for(record <- consumer.poll(100)){
            callback.process(record.key, record.value)
          }
        }
      }
    }).start()

  }
}

class KafkaTopicExampleConsumer extends KafkaConsumerCallback[String,String]{
  val LOG = LoggerFactory.getLogger(classOf[KafkaTopicExampleConsumer])
  override def process(key: String, value: String): Unit = {
    LOG.debug("example topic msg consumed with key:'{}' and value:'{}'", key:Any, value:Any)
  }
}
