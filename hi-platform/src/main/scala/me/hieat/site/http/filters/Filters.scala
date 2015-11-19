package me.hieat.site.http.filters

import javax.inject.Inject

import play.api.http.HttpFilters
import play.api.mvc.EssentialFilter

/**
 * Hieat Filters
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
class Filters @Inject()(tokenFilter: TokenFilter) extends HttpFilters {
  override def filters: Seq[EssentialFilter] = Seq(tokenFilter)
}
