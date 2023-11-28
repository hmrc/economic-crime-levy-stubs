package uk.gov.hmrc.economiccrimelevystubs.controllers

import play.api.mvc.Result
import play.api.test.FakeRequest
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase

import scala.concurrent.Future

class DmsSubmissionControllerSpec extends SpecBase {

  val controller = new DmsSubmissionController(cc)

  "submit" should {
    "return accepted response" in {

      val result: Future[Result] =
        controller.submit()(
          FakeRequest("POST", "")
        )

      status(result) shouldBe ACCEPTED
    }
  }
}
