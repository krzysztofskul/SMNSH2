package pl.krzysztofskul.smnsh2.init;

import java.util.List;

public interface InitDataGenerator<T> {
	
	List<T> initDataAndReturn();

}
