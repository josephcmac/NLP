package helpers

package object helpers {

    // naive recursive solution
    private val f_naive: (List[Char], List[Char]) => Int = (u, v) => {
        u match {
            case x::xs => {
                v match {
                    case y::ys => {
                        if (x == y) {
                            f_naive(xs, ys)
                        } else {
                            (2+f_naive(xs, ys)).min(1+f_naive(u, ys)).min(1+f_naive(xs, v))
                        }
                    }
                    case Nil => u.length
                }
            }
            case Nil => v.length
        }
    }

    // dynamic programming solution

    // t(i,j) = dist( u[0..i-1], v[0..j-1] )
    private val f_dp: (List[Char], List[Char]) => Int = (u, v) => {
        val m = u.length
        val n = v.length
        var t = Array.ofDim[Int](m+1, n+1)

        (0 to n).foreach(j => {
            t(0)(j) = j
        })

        (1 to m).foreach(i => (0 to n).foreach(j => {
            if (j == 0) {
                t(i)(0) = i
            } else {
                if ( u(i-1) == v(j-1)  ) {
                    t(i)(j) = t(i-1)(j-1)
                } else {
                    t(i)(j) = (2 + t(i-1)(j-1) ).min( 1 + t(i-1)(j) ).min( 1 + t(i)(j-1) )
                } 
            }
        }))
        
        t(m)(n)
    }

    // Levenshtein distance (const of insertion and deletion = 1, cost of substitution = 2)
    val dist: ((String, String)) => Int = R => R match {
        case (u,v) => f_dp(u.toList, v.toList)
    }
}