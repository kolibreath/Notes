
import java.math.BigDecimal;
import java.util.*;
import java.util.function.IntConsumer;

public class BatAlgorithm {

    private double[][] X; 		// Population/Solution (N x D)
    private double[][] V; 		// Velocities (N x D)
    private double[][] Q; 		// Frequency : 0 to Q_MAX (N x 1)
    private double[] F;			// Fitness (N)
    private double R; 			// Pulse Rate : 0 to 1
    private double A; 			// Loudness : A_MIN to A_MAX
    private double[][] lb;		// Lower bound (1 x D) for frequency
    private double[][] ub;		// Upper bound (1 x D) for frequency
    private double fmin; 		// Minimum fitness from F
    private double[] B;			// Best solution array from X (D)

    private final int N; 		// Number of bats
    private final int ITERATION_TIMES; 		// Number of iterations
    private final double Q_MAX;
    private final int D = 3;
    private final Random rand = new Random();

    private BatAlgorithm(int N, int ITERATION_TIMES, double A_MIN, double A_MAX, double R_MIN, double R_MAX){
        this.N = N;
        this.ITERATION_TIMES = ITERATION_TIMES;

        this.X = new double[N][D];
        this.V = new double[N][D];
        this.Q = new double[N][1];
        this.F = new double[N];
        this.R = (R_MAX + R_MIN) / 2;
        this.A = (A_MIN + A_MAX) / 2;

        // Initialize bounds
        this.lb = new double[1][D];
        for ( int i = 0; i < D; i++ ){
            this.lb[0][i] = -2.0;
        }
        this.ub = new double[1][D];
        for ( int i = 0; i < D; i++ ){
            this.ub[0][i] = 2.0;
        }

        // Initialize Q and V
        for ( int i = 0; i < N; i++ ){
            this.Q[i][0] = 0.0;
        }
        for ( int i = 0; i < N; i++ ){
            for ( int j = 0; j < D; j++ ) {
                this.V[i][j] = 0.0;
            }
        }

        // Initialize X
        for ( int i = 0; i < N; i++ ){
            for ( int j = 0; j < D; j++ ){
                this.X[i][j] = lb[0][j] + (ub[0][j] - lb[0][j]) * rand.nextDouble();
            }
            System.out.println("the location of Bats after init: "+Arrays.toString(X[i]));
            this.F[i] = objective(X[i]);
        }


        // Find initial best solution
        int fmin_i = 0;
        for ( int i = 0; i < N; i++ ){
            if ( F[i] < F[fmin_i] )
                fmin_i = i;
        }

        // Store minimum fitness and it's index.
        // B holds the best solution array[1xD]
        this.fmin = F[fmin_i];
        this.B = X[fmin_i]; // (1xD)
        Q_MAX = 2.0;
    }

    //求出当前列的最小平方和 ？
    //todo ? 找的是离原点最近的距离？？
    //求最小平方和和F[i]对应下表的值比较
    private double objective(double[] Xi){
        double sum = 0.0;
        for ( int i = 0; i < Xi.length; i++ ){
            sum += Xi[i] * Xi[i] ;
        }
        return sum;
    }

    private double[] simpleBounds(double[] Xi){
        // Don't know if this should be implemented
        double[] Xi_temp = new double[D];
        System.arraycopy(Xi, 0, Xi_temp, 0, D);

        for ( int i = 0; i < D; i++ ){
            if ( Xi_temp[i] < lb[0][i] )
                Xi_temp[i] = lb[0][i];
        }

        for ( int i = 0; i < D; i++ ){
            if ( Xi_temp[i] > ub[0][i] )
                Xi_temp[i] = lb[0][i];
        }
        return Xi_temp;
    }

    private void startBat(){

        double[][] S = new double[N][D];
        int n_iter = 0;

        // Loop for all iterations/generations(ITERATION_TIMES)
        for (int t = 0; t < ITERATION_TIMES; t++ ){
            // Loop for all bats(N)
            for ( int i = 0; i < N; i++ ){

                // Update frequency (Nx1)
                double q_MIN = 0.0;

                //qim - qmax?
                //随机往一个方向飞一飞
                double cur = q_MIN + (q_MIN -Q_MAX) * rand.nextDouble();
                Q[i][0] = cur;

                //如果确认这个位置是可行的 再进行移动！！
                // Update velocity (NxD)
                for ( int j = 0; j < D; j++ ){
                    double a = X[i][j] - B[j];
                    double b =V[i][j];
                    double c= Q[i][0];
                    double d = b + (a) * c;
                    V[i][j] = d;
                }
                // Update S = X + V
                for ( int j = 0; j < D; j++ ){
                    S[i][j] = X[i][j] + V[i][j];
                }
                // Apply bounds/limits
                X[i] = simpleBounds(X[i]);
                // Pulse rate
                if ( rand.nextDouble() > R )
                    for ( int j = 0; j < D; j++ )
                        X[i][j] = B[j] + 0.001 * rand.nextGaussian();


                // Evaluate new solutions
                double fnew = objective(X[i]);

                // Update if the solution improves or not too loud
                if ( fnew <= F[i] && rand.nextDouble() < A ){
                    X[i] = S[i];
                    F[i] = fnew;
                }

                // Update the current best solution
                if ( fnew <= fmin ){
                    B = X[i];
                    fmin = fnew;
                    System.out.println("current fmin+" + fmin);
                }
            } // end loop for N
            n_iter = n_iter + N;
//
//            for(double bats[]:X){
//                System.out.println("the location after "+t+"th iteration"+Arrays.toString(bats));
//            }
        } // end loop for ITERATION_TIMES


        //输出目标：随机蝙蝠距离原点的最小的距离？？

        List<Integer>  min= new LinkedList<>();
        for(int i = 0;i< X.length;i++){
            int sum = 0;
            for(int j = 0 ; j < X[0].length;j++){
                Double curDouble = X[i][j];
                int cur = curDouble.intValue();
                sum = (int) (sum +  cur* cur);
            }
            min.add(sum);
        }

        for(double bats[]:X){
                System.out.println("the location after iteration"+Arrays.toString(bats));
            }
        Collections.sort(min);
        System.out.println("the value of smallest to origin: " + ((LinkedList<Integer>) min).getLast());
        System.out.println("Number of evaluations : " + n_iter );
        System.out.println("Best = " + Arrays.toString(B) );
        System.out.println("Fitness = " + Arrays.toString(F) );
        System.out.println("the smallest of F = " + Arrays.stream(F).min() );
        System.out.println("the maxest of F = " + Arrays.stream(F).max() );
        System.out.println("fmin = " + fmin );
    }

    public static void main(String[] args) {
        new BatAlgorithm(3, 100, 0.0, 1.0, 0.0, 1.0).startBat();
    }
}
