package data;

import java.util.ArrayList;
import java.util.List;

public class Forest {
    List<Tree> forest = new ArrayList<>();

	public List<Tree> getForest() {
		return forest;
	}

	public void setForest(List<Tree> forest) {
		this.forest = forest;
	}

	public void addForest(Tree headOfForest){
		forest.add(headOfForest);
	}
	public int getForestNumber(){
		return forest.size();
	}
}
