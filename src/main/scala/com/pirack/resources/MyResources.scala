package com.pirack.resources

import javax.ws.rs._
import javax.ws.rs.core.{MediaType, Response}
import com.pirack.{KafkaTopicExampleConsumer, MyConfiguration}

import scala.util.Random


/**
  * Created by tmaus on 23/8/16.
  */
@Path("/api")
class MyResources(myConfiguration: MyConfiguration, exampleConsumer: KafkaTopicExampleConsumer) {

  @GET
  @Path("/index")
  def index() : Response = Response.ok("our index page").build()

  @PUT
  @Path("/publish/{msg}")
  def publish(@PathParam("msg") msg:String) : String = {
    val r = Random.nextString(5)
    exampleConsumer.process(r, msg)
    "published message with key:" + r + " and value: " + msg
  }

}
