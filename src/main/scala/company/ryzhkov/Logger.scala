package company.ryzhkov

import cats.InjectK
import cats.free.Free

sealed trait Logger[A]
case class Info(msg: String) extends Logger[Unit]

class LoggerOps[F[_]](implicit I: InjectK[Logger, F]) {
  def info(msg: String): Free[F, Unit] = Free.inject[Logger, F](Info(msg))
}

object LoggerOps {
  implicit def loggerOps[F[_]](implicit I: InjectK[Logger, F]): LoggerOps[F] = new LoggerOps[F]
}
