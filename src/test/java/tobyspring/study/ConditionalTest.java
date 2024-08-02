package tobyspring.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {
  @Test
  void conditional() {
    //true
    ApplicationContextRunner contextRunner = new ApplicationContextRunner();
    contextRunner.withUserConfiguration(Config1.class)
            .run(context -> {
              assertThat(context).hasSingleBean(MyBean.class);
              assertThat(context).hasSingleBean(Config1.class);
            });

    //false
    /* AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
    ac2.register(Config2.class);
    ac2.refresh();
    MyBean bean2 = ac2.getBean(MyBean.class); */

    ApplicationContextRunner contextRunner2 = new ApplicationContextRunner();
    contextRunner2.withUserConfiguration(Config2.class)
            .run(context -> {
              assertThat(context).doesNotHaveBean(MyBean.class);
              assertThat(context).doesNotHaveBean(Config2.class);
            });
  }

  //conditional을 메타 어노테이션으로 가진 나만의 어노테이션 만들기
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(BooleanCondition.class)
  @interface BooleanConditional {
    boolean value(); //어노테이션의 매개변수 정의(메서드 형식으로)
  }

  @Configuration
  @BooleanConditional(true)
   static class Config1 {
    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  @Configuration
  @BooleanConditional(false)
  static class Config2 {
    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  static class MyBean { }

  static class BooleanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
      Boolean value = (Boolean) annotationAttributes.get("value");
      return value;
    }
  }
}
