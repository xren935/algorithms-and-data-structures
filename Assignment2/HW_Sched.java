//created by Xingya Ren

import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * Return -1 if a1 > a2
	 * Return 1 if a1 < a2
	 * Return 0 if a1 = a2 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// TODO Implement this
		//sort by weight in descending order  
			//if there is a tie-->put the one that has larger deadline before 
		if(a1.weight < a2.weight) {
			return 1; 
		}else if(a1.weight > a2.weight) {
			return -1; 
		}else { 
			//two have the same weights  
			//the one with larger deadline comes first 
			if(a1.deadline < a2.deadline) {
				return 1; 
			}else {
				return -1; 
			}
		}
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this
		
		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		// If schedule[i] has a value -1, it indicates that the 
		// i'th timeslot in the schedule is empty
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}
		//STUDENT CODE STARTS HERE
			//add assign to the array 
			//if the next one should come before, then swap(?) 
		
		//loop through the sorted list 
			//get the largest deadline 
				//check if that corresponding slot is occupied 
					//if it is -->go to the previous slot 
					//if not --> assign the assignment 
		for(int i=1; i<=Assignments.size(); i++) {
			int theDdl = Assignments.get(i-1).deadline; 
			if(homeworkPlan[theDdl-1] == -1) {
				//there is currently no assignment assiged to this slot
				homeworkPlan[theDdl-1] = Assignments.get(i-1).number; 
			}else {
				//go to the previous slot 
				for(int j=theDdl-2; j>=0; j--) {
					if (homeworkPlan[j]==-1) {
						homeworkPlan[j] = Assignments.get(i-1).number;
						break;
					}
				}
			}
		}
		return homeworkPlan;
	}
}
	



