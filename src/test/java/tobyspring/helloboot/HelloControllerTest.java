import org.junit.jupiter.api.Test;
import tobyspring.helloboot.HelloController;

public class HelloControllerTest {
  @Test
  void helloController() {
    //고립 > 익명클래스 > 인터페이스가 하나니깐 우선은 람다
    HelloController helloController = new HelloController(name -> name);
  }
}
