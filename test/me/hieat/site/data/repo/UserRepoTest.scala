package me.hieat.site.data.repo

import me.hieat.site.data.DbWordPSpec

/**
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-05.
 */
class UserRepoTest extends DbWordPSpec {
  val userRepo = new UserRepo(schema)

  "UserRepoTest" should {

    "existsByPhone" in {
      userRepo.existsByPhone("13883712048").futureValue shouldBe 1
    }

  }
}
