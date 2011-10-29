/*! \addtogroup Examples Examples 
 * This module contains many examples of usage. 
 * @{ 
 */
package edu.mit.csail.sdg.squander.examples.numbers;

import java.util.Arrays;
import java.util.Random;

import edu.mit.csail.sdg.annotations.Case;
import edu.mit.csail.sdg.annotations.Ensures;
import edu.mit.csail.sdg.annotations.Modifies;
import edu.mit.csail.sdg.annotations.Requires;
import edu.mit.csail.sdg.annotations.Returns;
import edu.mit.csail.sdg.annotations.Specification;
import edu.mit.csail.sdg.squander.Squander;
import edu.mit.csail.sdg.squander.annotations.Options;

/**
 * Examples of arithmetic algorithms.
 * @author kuat
 */
public class Arithmetic {

    @Requires("m > 0 && n > 0")
    @Ensures({"no x : int | x > return && m % x = 0 && n % x = 0",
        "return > 0",
        "m % return = 0",
        "n % return = 0"})
    public static int gcd(final int m, final int n) {
        return Squander.exe(null, new Class<?>[] {int.class, int.class}, new Object[] {m, n});
    }

    @Requires("m > 0 && n > 0")
    @Ensures({"no x : int | x > return && m % x = 0 && n % x = 0",
        "return > 0",
        "m % return = 0",
        "n % return = 0"})
    public static int gcd_impl(int m, int n) {
        if (m < n) {
            int t = m;
            m = n;
            n = t;
        }

        int r = m % n;

        if (r == 0) {
            return n;
        } else {
            return gcd(n, r);
        }
    }

//    @Ensures( { "all i: int | #{j:int | return[j] = i} = #{j:int | a[j] = i}",
//                "all i: int | all j: int | 0 <= i && i < j && j < return.length => return[i] <= return[j]" })
//  @Fresh({@FreshObjects(cls=int[].class, num=1)})
//    public static int[] sort2(int[] a) {
//        return new Squander().magic(null, new Class[] {int[].class}, new Object[] {a});
//    }
    
    @Ensures( { "all i: int | #{j:int | a[j] = i} = #{j:int | @old(a[j]) = i}",
                "all i: int | all j: int | 0 <= i && i < j && j < a.length => a[i] <= a[j]" })
    @Modifies("a.elems")
    @Returns("a")
    public static int[] sort(int[] a) {
        return Squander.exe(null, new Class[] { int[].class }, new Object[] { a });
    }
    
    @Specification({
            @Case(
                    requires = "s = 0",
                    ensures = "return = 0"                        
            ),
            @Case(
                    requires = "s > 0",
                    ensures = "return > 0 && return <= s / return && s / (return + 1) < (return + 1)"
            )
    })                    
    public static int square_root(int s) {
        return Squander.exe(null, new Class<?>[] {int.class}, new Object[]{s});
    }

    
    // Carroll Morgan's square root development
    // return : [return^2 <= s < (return + 1)^2]
    @Requires("s >= 0")
    @Ensures({"return >= 0",
        "return <= s / return", 
        "s / (return + 1) < (return + 1)"})
    public static int square_root_finite(int s) {
        return Squander.exe(null, new Class<?>[] {int.class}, new Object[]{s});
    }
    
    public static int square_root_mixed(final int s) {
        int r = 1;
        int q = s; 
        while (r+1 < q) {
            int p = findP(r, q);
            if (s < p*p) {
                q = findQ(p, q, r, s);
            } else {
                r = findR(p, q, r, s);
            }
        }
        return r; 
    }

    @Requires("r + 1 < q")
    @Ensures("return > r && return < q")
    @Options(ensureAllInts = true)
    private static int findP(final int r, final int q) {
        return Squander.exe(null, new Class[] {int.class, int.class}, new Object[] {r, q});
    }
    
    @Requires({"r <= s/r && s/q < q", "s/p < p", "p < q"})
    @Ensures("return < q && return > 0 && r <= s/r && s/return < return")
    private static int findQ(final int p, final int q, final int r, final int s) {
        return Squander.exe(null, new Class[] {int.class, int.class, int.class, int.class}, 
                new Object[] {p, q, r, s});
    }
    
    @Requires({"r <= s/r && s/q < q", "s/p >= p", "r < p"})
    @Ensures("return > r && return  <= s/return && s/q < q")
    private static int findR(final int p, final int q, final int r, final int s) {
        return Squander.exe(null, new Class[] {int.class, int.class, int.class, int.class}, 
                new Object[] {p, q, r, s});
    }
    
    public static int _square_root_mixed(final int s) {
        int r = 1;
        int q = s; 
        while (r+1 < q) {
            int p = findP(r, q);
            if (s < p*p) {
                q = _findQ(p, q, r, s);
            } else {
                r = _findR(p, q, r, s);
            }
        }
        return r; 
    }

    @Requires({"r*r <= s && s < q*q", "s < p*p", "p < q"})
    @Ensures("return < q && return > 0 && r*r <= s && s < return * return")
    private static int _findQ(final int p, final int q, final int r, final int s) {
        return Squander.exe(null, new Class[] {int.class, int.class, int.class, int.class}, 
                new Object[] {p, q, r, s});
    }
    
    @Requires({"r <= s/r && s/q < q", "s >= p*p", "r < p"})
    @Ensures("return > r && return * return <= s && s < q*q")
    private static int _findR(final int p, final int q, final int r, final int s) {
        return Squander.exe(null, new Class[] {int.class, int.class, int.class, int.class}, 
                new Object[] {p, q, r, s});
    }
    
    public static void main(String[] args) {
        int n = 24;
        int[] a = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(n) - n/2;
        }
        System.out.println(Arrays.toString(a));
//        Squander.setDefaultOptions(6);
        long t1 = System.currentTimeMillis();
        a = sort(a);
        long t2 = System.currentTimeMillis();
        System.out.println(Arrays.toString(a));
        System.out.println("time: " + Double.toString((t2 - t1)/1000));
    }

}
/*! @} */
