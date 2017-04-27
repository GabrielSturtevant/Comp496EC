public class Problem1 {
    public static void main(String[] args) {
        int array_size = 50;
        int value_range = 20;
        int cycles = 100;
        long recur_time = 0;
        long iter_time = 0;
        long begin;
        long end;
        int[] sample;
        String output;
        for (int i = 0; i < cycles; i++) {
            int[] ans_info;
            sample = new int[array_size];
            for (int j = 0; j < array_size; j++) {
                sample[j] = (int)(Math.random() * value_range);
                int probability = (int)(Math.random()* 100);
                if(probability < 40){
                    sample[j] = sample[j] * -1;
                }
            }
            output = "Recursive:\n";
            begin = System.nanoTime();
            ans_info = recursive(sample, 0, sample.length - 1);
            end = System.nanoTime();
            recur_time += end - begin;
            output = output + "i: " + ans_info[0] + ", j:" + ans_info[1] + ", Total: " + ans_info[2] + "\n";

            output = output + "Iterative\n";
            begin = System.nanoTime();
            ans_info = iterative(sample);
            end = System.nanoTime();
            iter_time += end -begin;
            output = output + "i: " + ans_info[0] + ", j:" + ans_info[1] + ", Total: " + ans_info[2]+"\n";

            System.out.println(output);
        }
        iter_time = iter_time / cycles;
        recur_time = recur_time / cycles;
        long speedup = iter_time / recur_time;
        output = "\n\n";
        output = output + "Average Recursive: " + recur_time + "\n";
        output = output + "Average Iterative: " + iter_time + "\n";
        output = output + "Speedup: " + speedup;

        System.out.println(output);
    }

    public static int[] iterative(int[] a){
        int length = a.length;
        int[] answer_array = new int[]{0,0,0};
        int absolute_max = 0;
        int current_max;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                current_max = 0;
                for (int k = i; k <= j; k++) {
                    current_max += a[k];
                }
                if (current_max >= absolute_max){
                    absolute_max = current_max;
                    answer_array = new int[]{i, j, absolute_max};
                }
            }
        }
        return answer_array;
    }

    public static int[] crossing(int[] A, int low, int mid, int high) {
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

    public static int[] recursive(int[] A, int low, int high) {
        if (low == high) {
            return new int[]{low, high, A[low]};
        } else {
            int mid = Math.floorDiv(low + high, 2);
            return choose(recursive(A, low, mid),
                    recursive(A, mid + 1, high),
                    crossing(A, low, mid, high));
        }
    }

    public static int[] choose(int[] a, int[] b) {
        int first = a[2];
        int second = b[2];
        if(first > second){
            return a;
        } else {
            return b;
        }
    }

    public static int[] choose(int[] a, int[] b, int[] c) {
        return choose(choose(a, b), c);
    }
}
