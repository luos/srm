package luos.srm

import akka.actor.Actor

/**
 * Created by luos on 2015.08.23..
 */
// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class FrontendServiceActor extends Actor with FrontendService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute( frontendRoute )

}
