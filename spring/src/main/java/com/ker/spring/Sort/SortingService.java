package com.ker.spring.Sort;

import com.ker.spring.Util.StringUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class SortingService {
  private static final Logger LOGGER = LogManager.getLogger(SortingService.class);

  private SortingAlgorithm selectionSort;
  public void setSelectionSort(SortingAlgorithm selectionSort) {
    this.selectionSort = selectionSort;
  }

  @Autowired
  private StringUtil stringUtils;

  private void postConstructor(){
//    The PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization. This method MUST be invoked before the class is
//  put into service. This annotation MUST be supported on all classes that support dependency injection. The method annotated with PostConstruct MUST be invoked even if the class does not request
// any resources to be injected. Only one method can be annotated with this annotation.
    LOGGER.info(stringUtils.joinString("postConstructor", "method called"));
  }

  private void preDestroy(){
//    The PreDestroy annotation is used on methods as a callback notification to signal that the instance is in the process of being removed by the container. The method annotated with PreDestroy
//    is typically used to release resources that it has been holding. This annotation MUST be supported by all container managed objects that support PostConstruct
//    except the application client container in Java EE 5.
    LOGGER.warn("pre-destroy method called");
  }

  // no usages, putting it here to demonstrate that the constructor is not used by the spring framework
  public void SortingService(){
    System.out.println("Constructor called...");
  }

  public void sort(int[] numbers) {
    if (ArrayUtils.isEmpty(numbers)) return;
    try {
      LOGGER.trace("Before sorting: " + Arrays.toString(numbers));
      selectionSort.sortNumbers(numbers);
      LOGGER.trace("After sorting: " + Arrays.toString(numbers));
      LOGGER.info("SortingAlgorithm instance: {}", selectionSort);
    } catch (Exception e) {
      LOGGER.error("Encountered following error sorting numbers: " + e);
      e.printStackTrace();
    }
  }
}
