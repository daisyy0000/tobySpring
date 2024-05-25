import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tobyspring.helloboot.HelloController;

public class HelloControllerTest {
  @Test
  void helloController() {
    HelloController helloController = new HelloController(name -> name);
    String ret = helloController.hello("Test");
    Assertions.assertThat(ret).isEqualTo("Test");
  }

  @Test
  void failsHelloController() {
    HelloController helloController = new HelloController(name -> name); //테스트에서 사용하는 수동 DI
    //예외가 발생했음을 검증할 수 있는 메서드
    Assertions.assertThatThrownBy(() -> {
      helloController.hello(null);
    }).isInstanceOf(IllegalArgumentException.class);

    Assertions.assertThatThrownBy(() -> {
      helloController.hello("");
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
