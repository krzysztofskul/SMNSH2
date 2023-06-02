package pl.krzysztofskul.sensit.smnsh.init;

import java.util.List;

public interface InitDataGenerator<T> {
	
	List<T> initDataAndReturn();

}
