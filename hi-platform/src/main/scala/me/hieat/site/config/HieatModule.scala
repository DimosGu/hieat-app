package me.hieat.site.config

import com.google.inject.AbstractModule
import me.hieat.site.HieatSystem

/**
 * HieatModule
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
class HieatModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[HieatSystem]).asEagerSingleton()
  }
}
