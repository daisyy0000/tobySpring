package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
public class HelloRepositoryTest {
  @Autowired
  HelloRepository helloRepository;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  void findHelloFailed() {
    Assertions.assertThat(helloRepository.findHello("Toby")).isNull();
  }

  @Test
  void increaseCount() {
    helloRepository.increaseCount("Toby");
    Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(1);

    helloRepository.increaseCount("Toby");
    Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
  }
}
