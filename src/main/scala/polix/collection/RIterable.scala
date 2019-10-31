package polix.collection

import scala.language.higherKinds
import scala.language.reflectiveCalls
import cats.Functor
import polix.reactive.Scannable

trait RIterable[+A, +G[_]] extends RIterableOps[A, G, RIterable, RIterable[A, G]] {
  def stream: G[M]
}

trait RIterableOps[+A, +G[_], +CC[_, _[_]], +C] {
  type M

  def map[B, G2[x] >: G[x] : Functor](f: A => B): CC[B, G2]

  def sorted[A2 >: A, G2[x] >: G[x] : Scannable](implicit ord: Ordering[A2]): CC[A2, G2]
}
