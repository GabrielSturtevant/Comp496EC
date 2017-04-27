import java.util.Arrays;
public class EC2 {
    int W;
    int[] weights;
    int[] benefits;
    int length;
    int[] ans_info;

    public static void main(String[] args) {
        int W = 100;
        int[] weights = {9,16,12,8,7, 16,7,8,9,14, 15,19,22,2,4,  5,10,11,3,17 ,15,18,15,9,7 };
        int[] benefits = {1,7,3,4,5,  5,3,4,6,2,   6,6,4,2,1,  3,12,2,4,5,   4,3,2,1,3};
        EC2 instance = new EC2(W, weights, benefits);
        instance.idealKnapsack();

        int finalWeight = 0;
        int finalValue = 0;
        for (int i = 0; i < instance.length; i++) {
            if(instance.ans_info[i] == 1){
                System.out.print(i + " ");
                finalWeight += instance.weights[i];
                finalValue += instance.benefits[i];
            }
        }
        System.out.println("\nFinal value: " + finalValue);
        System.out.println("Final weight: " + finalWeight);
    }

    public EC2(int W, int[]weights, int[]benefits){
        this.W = W;
        this.weights = weights;
        this.benefits = benefits;
        this.length = benefits.length;
        this.ans_info = new int[this.length];
        Arrays.fill(this.ans_info,0);
    }

    public static int power_of_n(int n){
        int val = 1;
        for (int i = 0; i < n; i++) {
            val = val * 2;
        }
        return val;
    }

    private void idealKnapsack(){
        int[] A = this.ans_info.clone();
        int[] ans_info = A.clone();
        int weight;
        int value;
        int best = 0;
        int j;
        int cycles = power_of_n(this.length);
        for (int i = 0; i < cycles; i++) {
            j = this.length - 1;
            weight = value = 0;
            while (A[j] != 0 && j > 0){
                A[j] = 0;
                j -= 1;
            }
            A[j] = 1;
            for (int k = 0; k < this.length; k++) {
                if(A[k] == 1){
                    weight += this.weights[k];
                    value += this.benefits[k];
                }
            }
            if ((value > best) && (weight < this.W)){
                best = value;
                ans_info = A.clone();
            }
        }
         this.ans_info = ans_info.clone();
    }
}
