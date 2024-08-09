package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class HelloDecorator implements HelloService {
  //HelloSerivce 인터페이스를 구현한 클래스이면서 동시에 HelloService를 구현한 또 다른 오브젝트를 의존해야 함.
  private final HelloService helloService;

  public HelloDecorator(HelloService helloService) {
    this.helloService = helloService;
  }

  @Override
  public String sayHello(String name) {
    return "*" + helloService.sayHello(name) + "*";
  }

  @Override
  public int countOf(String name) {
    return helloService.countOf((name));
  }
}
