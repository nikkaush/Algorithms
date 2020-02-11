/*
 * Stable.java
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stable {
	/**
	 * @author Nikhil Kaushik (nk2214@rit.edu)
	 */

	public static void main(String[] args) {
		int numberOfMenWomen = 0;
		List<List<Integer>> menPreferences = new ArrayList<List<Integer>>();
		List<List<Integer>> womenPreferences = new ArrayList<List<Integer>>();
		int[] nextProposal = null;
		List<Integer> menPair = new ArrayList<>();
		List<Integer> womenPair = new ArrayList<>();

		try (Scanner sc = new Scanner(System.in);) {

			while (sc.hasNext()) {
				String line = sc.nextLine().trim();
				int index = line.indexOf("#");
				if (index > -1)
					line = line.substring(0, index).trim();
				if (!line.isEmpty()) {
					System.out.println(line/* +" "+line.length() */);
					if (line.length() == 1) {
						numberOfMenWomen = Integer.parseInt(line);
						nextProposal = new int[numberOfMenWomen];
					} else {
						String[] tempArr = line.split(" ");
						List<Integer> tempList = new ArrayList<>();
						for (int i = 1; i <= numberOfMenWomen; i++) {
							tempList.add(Integer.parseInt(tempArr[i]) - 1);
						}
						if (menPreferences.size() < numberOfMenWomen) {
							menPreferences.add(tempList);
							menPair.add(menPreferences.size() - 1);
							womenPair.add(-1);
						} else
							womenPreferences.add(tempList);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception while reading");
		}

		while (womenPair.contains(-1)) {

			for (int i = 0; i < numberOfMenWomen; i++) {
				if (womenPair.get(i) == -1) {
					int women = menPreferences.get(i).get(nextProposal[i]);
					nextProposal[i]++;
					if (womenPair.contains(women)) {
						int engagedMen = menPair.get(womenPair.indexOf(women));
						List<Integer> l = womenPreferences.get(women);
						if (l.indexOf(engagedMen) > l.indexOf(i)) {
							womenPair.set(i, women);
							womenPair.set(engagedMen, -1);
						}

					} else {
						womenPair.set(i, women);
					}
				}

			}

		}
		for (int i = 0; i < numberOfMenWomen; i++) {
			String line = "m" + (menPair.get(i) + 1) + " " + "w" + (womenPair.get(i) + 1);
			System.out.println(line);
		}

	}

} //Stable
