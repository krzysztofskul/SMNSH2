package pl.krzysztofskul.smnsh2.project.milestone;

import java.util.Comparator;

public class MilestoneComparator implements Comparator<MilestoneInstance> {

	@Override
	public int compare(MilestoneInstance m1, MilestoneInstance m2) {
		
		return (int) (m1.getDeadline().toEpochDay()-m2.getDeadline().toEpochDay());
	}

}
