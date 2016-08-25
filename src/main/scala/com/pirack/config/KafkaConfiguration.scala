package com.pirack.config

import java.util
import java.util.Properties

import com.fasterxml.jackson.annotation.JsonProperty
import com.datasift.dropwizard.scala.validation.constraints._
import scala.collection.JavaConversions._


/**
  * Created by tmaus on 17/8/16.
  */


class KafkaConfiguration{
  @NotNull @JsonProperty("bootstrap.servers") var bootstrapServers: String = _
  @NotNull @JsonProperty("group.id") var groupId: String = _
  @NotNull @JsonProperty("enable.auto.commit") var enableAutoCommit: String = _
  @NotNull @JsonProperty("auto.commit.interval.ms") var autoCommitInterval: String = _
  @NotNull @JsonProperty("session.timeout.ms") var sessionTimeout: String = _
  @NotNull @JsonProperty("key.deserializer") var keyDeserializer:String = "org.apache.kafka.common.serialization.StringDeserializer"
  @NotNull @JsonProperty("value.deserializer") var valueDeserializer:String = "org.apache.kafka.common.serialization.LongDeserializer"
  @NotNull @JsonProperty("topics") var topics:util.Collection[String] = _

  @NotNull @JsonProperty("client.id") var clientId:String  = _
  @NotNull @JsonProperty("producer.type") var producerType:String  = _
  @NotNull @JsonProperty("acks") var acks:String  = _
  @NotNull @JsonProperty("retries") var retries:Integer = _
  @NotNull @JsonProperty("linger.ms") var lingerMs:java.lang.Long = _
  @NotNull @JsonProperty("batch.size") var batchSize:String = _
  @NotNull @JsonProperty("buffer.memory") var bufferMemory:java.lang.Long = _
  @NotNull @JsonProperty("key.serialiser") var keySerializer:String = "org.apache.kafka.common.serialization.StringSerializer"
  @NotNull @JsonProperty("value.serialiser") var valueSerializer:String = "org.apache.kafka.common.serialization.StringSerializer"

  def getConsumerProps() : Properties = {
    val props = new Properties()
    props.put("bootstrap.servers", bootstrapServers)
    props.put("group.id", groupId)
    props.put("enable.auto.commit", enableAutoCommit)
    props.put("auto.commit.interval.ms", autoCommitInterval)
    props.put("session.timeout.ms", sessionTimeout)
    props.put("key.deserializer", keyDeserializer)
    props.put("value.deserializer", valueDeserializer)
    props
  }

  def getProducerProps() : Properties = {
    val props = new Properties()
    props.put("bootstrap.servers", bootstrapServers)
    props.put("acks", acks)
    props.put("retries", retries)
    props.put("batch.size", batchSize)
    props.put("linger.ms", lingerMs)
    props.put("buffer.memory", bufferMemory)
    props.put("key.serializer", keySerializer)
    props.put("value.serializer", valueSerializer)
    props
  }
}