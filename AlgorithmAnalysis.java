package Assignment;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class AlgorithmAnalysis {
    //  create a universal array that all the functions can use
    public static int[] generateS(int size) {
        int[] S = new int[size];
        for (int j = 0; j < size; j++) {
            S[j] = (int) (Math.random() * (100 * size - 10 * size + 1) + 10 * size);
        }

        return S;
    }

    public static ArrayList<Integer> deviation(int[] S, ArrayList<Integer> S1, ArrayList<Integer> S2) {
        ArrayList<Integer> deviations = new ArrayList<>();
        int idealSum;
        int sum;
        int sumOfS1;
        int sumOfS2;
        int diffS1;
        int diffS2;
        sumOfS1 = S1.stream().mapToInt(Integer::intValue).sum();// sum of all in S1
        sumOfS2 = S2.stream().mapToInt(Integer::intValue).sum();//  //  // //  S2
        sum = Arrays.stream(S).sum();
        idealSum = sum / 2;

        diffS1 = (Math.abs(sumOfS1 - idealSum));
        diffS2 = (Math.abs(sumOfS2 - idealSum));
        deviations.add(Math.min(diffS1, diffS2));

        return deviations;
    }

    public static ArrayList<Integer> algorithmA(int[] S) {
        int idealSum;
        int sum;
        int sumOfS1;
        int sumOfS2;
        int diffS1;
        int diffS2;
        ArrayList<Integer> deviations = new ArrayList<>();
        int midPoint = S.length / 2;
        int[] S1 = new int[midPoint];// partition 1
        int[] S2 = new int[S.length - midPoint]; //partition 2

        System.arraycopy(S, 0, S1, 0, midPoint);
        System.arraycopy(S, midPoint, S2, 0, S.length - midPoint);
        System.out.println("S1 for algorithm A = " + Arrays.toString(S1));
        System.out.println("S2 for algorithm A = " + Arrays.toString(S2));

        sumOfS1 = Arrays.stream(S1).sum();// find the sum of everything in S1
        sumOfS2 = Arrays.stream(S2).sum();// find the sum of everything in S2
        System.out.println("sum of S1 for algorithm A = " + sumOfS1);
        System.out.println("sum of S2 for algorithm A= " + sumOfS2);

        sum = Arrays.stream(S).sum();// find the sum of everything in the S array
        idealSum = sum / 2; // Divide by 2 to find the ideal sum
        System.out.println("ideal sum for algorithm A= " + idealSum);

        diffS1 = (Math.abs(sumOfS1 - idealSum));
        diffS2 = (Math.abs(sumOfS2 - idealSum));// find the absolute difference between each partition and the ideal sum
        deviations.add(Math.min(diffS1, diffS2));
        System.out.println("deviation S1 for algorithm A= " + diffS1);

        return deviations;
    }

    public static ArrayList<Integer> algorithmB(int[] S) {
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>();
        for (int i = 0; i < S.length; i++) {
            if (i % 2 == 0) {
                S1.add(S[i]);// partition 1
            } else {
                S2.add(S[i]);// partition 2
            }
        }
        System.out.println("items in S1 = " + S1);
        System.out.println("items in S2 = " + S2);
        return deviation(S, S1, S2);
    }

    public static ArrayList<Integer> algorithmC(int[] S) {
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>();
        for (int value : S) {
            if (value % 2 == 0) { // check for elements in even array indices
                S1.add(value);//partition 1
            } else {
                S2.add(value);//partition 2
            }
        }
        System.out.println("items in S1 = " + S1);
        System.out.println("items in S2 = " + S2);
        return deviation(S, S1, S2);
    }

    public static ArrayList<Integer> algorithmD(int[] S) {
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>();
        ArrayList<Integer> smallerSumPart;
        S1.add(S[0]);// Partition 1
        S2.add(S[1]);// partition 2
        //iterate through the remaining values and add them to whichever partition currently has the smallest sum
        for (int i = 2; i < S.length; i++) {
            smallerSumPart = S1.stream().mapToInt(Integer::intValue).sum() < S2.stream().mapToInt(Integer::intValue).sum() ? S1 : S2;
            smallerSumPart.add(S[i]);
        }
        System.out.println("items in S1 = " + S1);
        System.out.println("items in S2 = " + S2);
        return deviation(S, S1, S2);
    }

    public static ArrayList<Integer> algorithmE(int[] S) {
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>();
        Arrays.sort(S);// sort array in ascending order
        for (int i = 0; i < S.length; i++) {
            if (i % 2 == 0) {
                S1.add(S[i]);// even array indices
            } else {
                S2.add(S[i]);// partition for elements at Odd array indices
            }
        }
        System.out.println("items in S1 = " + S1);
        System.out.println("items in S2 = " + S2);
        return deviation(S, S1, S2);
    }

    public static ArrayList<Integer> algorithmF(int[] S) {
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>();
        Arrays.sort(S);// sort the array
        ArrayList<Integer> smallerSumPart;
        S1.add(S[0]);//partition one
        S2.add(S[1]);// partition two
        //iterate through the remaining values and add them to whichever partition currently has the smallest sum
        for (int i = 2; i < S.length; i++) {
            smallerSumPart = S1.stream().mapToInt(Integer::intValue).sum() < S2.stream().mapToInt(Integer::intValue).sum() ? S1 : S2;
            smallerSumPart.add(S[i]);
        }
        System.out.println("items in S1 = " + S1);
        System.out.println("items in S2 = " + S2);
        return deviation(S, S1, S2);
    }

    public static ArrayList<Integer> algorithmG(int[] S) {
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>(); //partitions 1 and 2
        Integer[] sortedS = Arrays.stream(S).boxed().toArray(Integer[]::new);// has to be changed to type Integer to be able to modify the array
        Arrays.sort(sortedS, Collections.reverseOrder());// Sort array in descending order
        S = Arrays.stream(sortedS).mapToInt(Integer::intValue).toArray();// S changed back to type int
        ArrayList<Integer> smallerSumPart;
        S1.add(sortedS[0]);//partition one
        S2.add(sortedS[1]);// partition two
        //iterate through the remaining values and add them to whichever partition currently has the smallest sum
        for (int i = 2; i < sortedS.length; i++) {
            smallerSumPart = S1.stream().mapToInt(Integer::intValue).sum() < S2.stream().mapToInt(Integer::intValue).sum() ? S1 : S2;
            smallerSumPart.add(S[i]);
        }
        System.out.println("items in S1 = " + S1);
        System.out.println("items in S2 = " + S2);
        return deviation(S, S1, S2);
    }

    public static ArrayList<Integer> algorithmH(int[] S) {
        int idealSum;
        int sum;
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>();
        sum = Arrays.stream(S).sum();
        idealSum = sum / 2;
        System.out.println("items in array  = " + Arrays.toString(S));
        System.out.println("ideal sum  = " + idealSum);
        int numOfSubsets = (1 << S.length) - 1;// same as saying 2^the length of S minus 1(the empty set )
        for (int i = 0; i < numOfSubsets; i++) {
            S1.clear();
            S2.clear();// clear the partitions for each iteration
            for (int j = 0; j < S.length; j++) {// iterates through all the elements in the input array
                if ((i & (1 << j)) != 0) {// uses bitwise operation to check which partition each of the jth element should be added to
                    S1.add(S[j]);
                } else {
                    S2.add(S[j]);
                }
                System.out.println("items in S1 = " + S1);
                System.out.println("items in S2 = " + S2);
            }
        }

        return deviation(S, S1, S2);
    }

    public static ArrayList<Integer> algorithmI(int[] S) {
        // This algorithm(I) is a dynamic programming algorithm
        int n = S.length;
        int idealSum;
        int sum;
        sum = Arrays.stream(S).sum();
        idealSum = sum / 2;
        boolean[] dp = new boolean[idealSum + 1];
        dp[0] = true;// this algorithm always considers empty sets, it uses empty sets as a starting point to determine partitions

        // Fill the DP table
        for (int k : S) {
            for (int j = idealSum; j >= k; j--) {
                dp[j] = dp[j] || dp[j - k];// check if the current sum is the same as the existing sum in the table or
                // is equal to the ideal sum and add that to the DP table
            }
        }
        // Find the largest possible sum, of one of the partitions that is less than or equal to the ideal sum
        int bestSum = -1;// will be updated later in the loop
        for (int j = idealSum; j >= 0; j--) {
            if (dp[j]) {//check if the value at the index j of the DP table is true.
                //the best sum becomes the sum in the DP table. at this point there is only one because the algorithm began with an empty set
                bestSum = j;// updated
                break;
            }
        }
        // Create the partitions using the DP table i.e., backtrack
        ArrayList<Integer> S1 = new ArrayList<>(), S2 = new ArrayList<>();
        for (int i = n - 1, j = bestSum; i >= 0; i--) {// start with last element in the input array and iterate in reverse order
            if (j >= S[i] && dp[j - S[i]]) {
                S1.add(S[i]);
                j -= S[i];
            } else {
                S2.add(S[i]);
            }
        }

        return deviation(S, S1, S2); // then get the deviations of the sums of S1 & S2 from the ideal
    }

    public static ArrayList<Integer> testAlgorithms() {
        //this method is for testing purposes
        int[] size = {8, 16, 32, 64, 128, 256, 512, 1024};// array sizes for  A through G
        int[] hSize = {8, 16,32,64,128,256};// for H and I
        ArrayList<Integer> deviate = new ArrayList<>();
        for (int setSize : size) {
            int[] arr = generateS(setSize);
            deviate.addAll(algorithmA(arr));
            deviate.addAll(algorithmB(arr));
            deviate.addAll(algorithmC(arr));
            deviate.addAll(algorithmD(arr));
            deviate.addAll(algorithmE(arr));
            deviate.addAll(algorithmF(arr));
            deviate.addAll(algorithmG(arr));// collect the deviations

        }
        for (int x : hSize) {
            int[] s = generateS(x);
            deviate.addAll(algorithmH(s));
            deviate.addAll(algorithmI(s));
        }

        return deviate;
        // print the deviate list if you want to see the deviations
    }

    public static void testHarness() {
        int iteration = 300;
        String[] algorithmList = {"A", "B", "C", "D", "E", "F", "G"};// names of the algorithm
        String[] hAndIAlgorithmList = {"H", "I"};
        int[] cardinalityList = {8, 16, 32, 64, 128, 256, 512, 1024};
        int[] hAndISizeList = {8, 16,32,64,128,256};

        try (FileWriter fileWriter = new FileWriter("result.csv")) {
            // Write the header row
            fileWriter.append("Cardinality").append(",").append("Algorithm").append(",").append("Average Deviation").append("\n");

            // For algorithms A to G
            for (int setSize : cardinalityList) {
                for (int algIdx = 0; algIdx < algorithmList.length; algIdx++) {// loop through the names of the algorithms
                    String algorithm = algorithmList[algIdx];
                    List<Double> deviations = new ArrayList<>();

                    for (int i = 0; i < iteration; i++) {
                        int[] arr = generateS(setSize);// generate array content
                        ArrayList<Integer> deviation;

                        switch (algIdx) {
                            case 0: deviation = algorithmA(arr); break;
                            case 1: deviation = algorithmB(arr); break;
                            case 2: deviation = algorithmC(arr); break;
                            case 3: deviation = algorithmD(arr); break;
                            case 4: deviation = algorithmE(arr); break;
                            case 5: deviation = algorithmF(arr); break;
                            case 6: deviation = algorithmG(arr); break;
                            default: deviation = new ArrayList<>();
                        }

                        deviations.add((double) deviation.get(0));
                    }

                    double averageDeviation = deviations.stream().mapToDouble(Double::doubleValue).average().orElse(0);// find the mean
                    fileWriter.append(String.valueOf(setSize)).append(",").append(algorithm).append(",").append(String.valueOf(averageDeviation)).append("\n");// write to file
                }
            }

            // For algorithms H and I
            for (int setSize : hAndISizeList) {
                for (int algIdx = 0; algIdx < hAndIAlgorithmList.length; algIdx++) {
                    String algorithm = hAndIAlgorithmList[algIdx];
                    List<Double> deviations = new ArrayList<>();

                    for (int i = 0; i < iteration; i++) {
                        int[] arr = generateS(setSize);
                        ArrayList<Integer> deviation;

                        switch (algIdx) {
                            case 0: deviation = algorithmH(arr); break;
                            case 1: deviation = algorithmI(arr); break;
                            default: deviation = new ArrayList<>();
                        }

                        deviations.add((double) deviation.get(0));
                    }

                    double averageDeviation = deviations.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                    fileWriter.append(String.valueOf(setSize)).append(",").append(algorithm).append(",").append(String.valueOf(averageDeviation)).append("\n");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
         testHarness();

    }
}








