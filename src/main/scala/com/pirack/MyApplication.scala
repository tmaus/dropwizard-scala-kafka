package com.pirack

import java.util
import javax.validation.constraints.NotNull

import com.datasift.dropwizard.scala.ScalaApplication
import com.fasterxml.jackson.annotation.JsonProperty
import com.pirack.config.KafkaConfiguration
import com.pirack.resources.MyResources
import io.dropwizard.Configuration
import io.dropwizard.setup.{Bootstrap, Environment}
import io.dropwizard.views.ViewBundle
import scala.collection.JavaConversions._

/**
  * Created by tmaus on 23/8/16.
  */

class MyConfiguration extends Configuration{
  @NotNull @JsonProperty("kafka") var kafkaConfiguration: KafkaConfiguration = _

  var _viewRendererConfiguration:util.Map[String, util.Map[String,String]] = _

  @JsonProperty("viewRendererConfiguration")
  def viewRendererConfiguration (viewRendererConfiguration:Map[String, Map[String, String]]) = {
    var map = new util.HashMap[String, util.Map[String, String]]()
    for( (k,v) <- viewRendererConfiguration)  map + (k->v)
    _viewRendererConfiguration = map
  }
}

object MyApplication extends ScalaApplication[MyConfiguration]{
  override def run(conf: MyConfiguration, env: Environment): Unit = {
    val exampleConsumer = new KafkaTopicExampleConsumer
    env.lifecycle().manage(new KafkaManager(conf,  exampleConsumer, "example"))
    env.jersey().register(new MyResources(conf, exampleConsumer))
  }

  override def init(bootstrap: Bootstrap[MyConfiguration]): Unit = {
    bootstrap.addBundle(new ViewBundle[MyConfiguration](){
      override def getViewConfiguration(configuration: MyConfiguration): util.Map[String, util.Map[String, String]] = configuration._viewRendererConfiguration
    })
  }
}
