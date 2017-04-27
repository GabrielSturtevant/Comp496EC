import java.util.Arrays;
public class Problem2 {
    int W;
    int[] weights;
    int[] benefits;
    int size;
    int[] answer;

    public static void main(String[] args) {
        int W = 100;
        int[] weights = {9,16,12,8,7, 16,7,8,9,14, 15,19,22,2,4,  5,10,11,3,17 ,15,18,15,9,7 };
        int[] benefits = {1,7,3,4,5,  5,3,4,6,2,   6,6,4,2,1,  3,12,2,4,5,   4,3,2,1,3};
        Problem2 test = new Problem2(W, weights, benefits);

        int finalWeight = 0;
        int finalValue = 0;
        String toPrint;
        for (int i = 0; i < test.size; i++) {
            if(test.answer[i] == 1){
                toPrint = i + ", Weight: " + test.weights[i];
                toPrint += ", Value: " + test.benefits[i];
                System.out.println(toPrint);
                finalWeight += test.weights[i];
                finalValue += test.benefits[i];
            }
        }
        System.out.println("Final value: " + finalValue);
        System.out.println("Final weight: " + finalWeight);
    }

    public Problem2(int W, int[]weights, int[]benefits){
        this.W = W;
        this.weights = weights;
        this.benefits = benefits;
        this.size = benefits.length;
        this.answer = new int[this.size];
        Arrays.fill(this.answer,0);
        this.answer = this.run();
    }

    private int[] run(){
        int[] A = this.answer.clone();
        int[] toReturn = A.clone();
        int tempWeight;
        int tempValue;
        int bestValue = 0;
        int j;
        int runs = exponential(this.size);
        for (int i = 0; i < runs; i++) {
            j = this.size - 1;
            tempWeight = tempValue = 0;
            while (A[j] != 0 && j > 0){
                A[j] = 0;
                j -= 1;
            }
            A[j] = 1;
            for (int k = 0; k < this.size; k++) {
                if(A[k] == 1){
                    tempWeight += this.weights[k];
                    tempValue += this.benefits[k];
                }
            }
            if ((tempValue > bestValue) && (tempWeight < this.W)){
                bestValue = tempValue;
                toReturn = A.clone();
            }
        }
        return toReturn;
    }

    public static int exponential(int n){
        if(n == 0){
            return 1;
        } else {
            return 2 * exponential(n - 1);
        }
    }
}
