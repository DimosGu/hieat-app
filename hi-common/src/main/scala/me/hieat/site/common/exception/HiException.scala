package me.hieat.site.common.exception

/**
 * 自定义异常
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
class HiException(val message: String, val code: Int, val cause: Throwable) extends RuntimeException(message, cause)

case class HiBadRequestException(override val message: String,
                                 override val code: Int = 400,
                                 override val cause: Throwable = null) extends HiException(message, code, cause)

case class HiUnauthorizedException(override val message: String,
                                   override val code: Int = 401,
                                   override val cause: Throwable = null) extends HiException(message, code, cause)

case class HiForbiddenException(override val message: String,
                                override val code: Int = 403,
                                override val cause: Throwable = null) extends HiException(message, code, cause)


case class HiNotFoundException(override val message: String,
                               override val code: Int = 404,
                               override val cause: Throwable = null) extends HiException(message, code, cause)

case class HiConflictException(override val message: String,
                               override val code: Int = 409,
                               override val cause: Throwable = null) extends HiException(message, code, cause)

case class HiInternalServerException(override val message: String,
                                     override val code: Int = 500,
                                     override val cause: Throwable = null) extends HiException(message, code, cause)
