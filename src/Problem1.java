public class Problem1 {
    public static void main(String[] args) {
        int size = 50;
        int maxValue = 20;
        int runs = 100;
        long fastTime = 0;
        long slowTime = 0;
        for (int i = 0; i < runs; i++) {
            int[] array = new int[size];
            for (int j = 0; j < size; j++) {
                array[j] = (int)(Math.random() * maxValue);
                if(Math.random() < .7){
                    array[j] *= -1;
                }
            }
            int[] answer;
//            printSlice(array, 0, size -1);
//            System.out.println("Fast");
            long start = System.nanoTime();
            answer = MaxSubArrayFast(array, 0, array.length - 1);
            long finish = System.nanoTime();
            fastTime += finish - start;
//            System.out.println("i: " + answer[0] + ", j:" + answer[1] + ", Total: " + answer[2]);
//            printSlice(array, answer[0], answer[1]);
//            System.out.println("\nSlow");

            start = System.nanoTime();
            answer = MaxSubSlow(array);
            finish = System.nanoTime();
            slowTime += finish -start;
//            System.out.println("i: " + answer[0] + ", j:" + answer[1] + ", Total: " + answer[2]);
//            printSlice(array, answer[0], answer[1]);
//            System.out.println("\n");
        }
        slowTime /= runs;
        fastTime /= runs;
        long mult = slowTime / fastTime;
        System.out.println("\n\n");
        System.out.println("Average Fast: " + fastTime);
        System.out.println("Average Slow: " + slowTime);
        System.out.println("Speedup: " + mult);
    }

    public static void printSlice(int[] A, int a, int b) {
        System.out.print("[");
        int count = 1;
        for (int i = a; i <= b; i++) {
            if (i != b)
                System.out.print(A[i] + ", ");
            else
                System.out.println(A[i] + "]");
            if(count++ % 30 == 0){
                System.out.println();
            }
        }
    }

    public static int[] Max(int[] A, int[] B, int[] C) {
        return Max(Max(A, B), C);
    }

    public static int[] Max(int[] A, int[] B) {
        return (A[2] > B[2]) ? A : B;
    }

    public static int[] MaxSubArrayFast(int[] A, int low, int high) {
        if (low == high) {
            return new int[]{low, high, A[low]};
        } else {
            int mid = Math.floorDiv(low + high, 2);
            return Max(MaxSubArrayFast(A, low, mid),
                    MaxSubArrayFast(A, mid + 1, high),
                    MaxCrossing(A, low, mid, high));
        }
    }

    public static int[] MaxCrossing(int[] A, int low, int mid, int high) {
        int leftSum = -Integer.MAX_VALUE;
        int rightSum = -Integer.MAX_VALUE;
        int sum = 0;
        int maxLeft = mid;
        int maxRight = mid + 1;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }

    public static int[] MaxSubSlow(int[] A){
        int size = A.length;
        int[] maxArr = new int[]{0,0,0};
        int max = 0;
        int runningMax;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                runningMax = 0;
                for (int k = i; k <= j; k++) {
                    runningMax += A[k];
                }
                if (runningMax >= max){
                    max = runningMax;
                    maxArr = new int[]{i, j, max};
                }
            }
        }
        return maxArr;
    }
}
