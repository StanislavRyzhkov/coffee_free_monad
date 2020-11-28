package company.ryzhkov

import cats.InjectK
import cats.free.Free

sealed trait Sender[A]
case class SendMessage(msg: String) extends Sender[Unit]

class SenderOps[F[_]](implicit I: InjectK[Sender, F]) {
  def sendMessage(msg: String): Free[F, Unit] = Free.inject[Sender, F](SendMessage(msg))
}

object SenderOps {
  implicit def denderOps[F[_]](implicit I: InjectK[Sender, F]): SenderOps[F] = new SenderOps[F]
}
