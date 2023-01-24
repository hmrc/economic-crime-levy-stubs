package uk.gov.hmrc.economiccrimelevystubs.repositories

import org.scalatest.OptionValues
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.gov.hmrc.mongo.test.DefaultPlayMongoRepositorySupport

import scala.concurrent.ExecutionContext.Implicits.global

class SequenceRepositorySpec
    extends AnyWordSpec
    with Matchers
    with DefaultPlayMongoRepositorySupport[MongoSequence]
    with ScalaFutures
    with IntegrationPatience
    with OptionValues {

  private val testKey: String = "test-id"

  protected override val repository = new SequenceRepository(
    mongoComponent = mongoComponent
  )

  "getNextSequenceId" should {
    "get the next sequence ID for the given key" in {
      val expectedResult = 1L

      val result: Long = repository.getNextSequenceId(testKey).futureValue

      result shouldEqual expectedResult
    }
  }

}
