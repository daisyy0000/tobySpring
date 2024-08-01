package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
  private final ClassLoader classLoader;

  public MyAutoConfigImportSelector(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }


  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    //ImportCandidates을 들어가서 보면 타입이 Iterable<String>!
    List<String> autoConfigs = new ArrayList<>();

    ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candinate ->
      autoConfigs.add(candinate)
    );

    //return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
    return autoConfigs.toArray(new String[0]);
  }
}
