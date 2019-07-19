import advertisement.{AdPublisher, AdSlot, ImageAdSlot, ImageAdvertisement, VideoAdSlot, VideoAdvertisement}

import org.scalatest.FunSuite

class TestAdvertisementSales extends FunSuite {
  test("test Advertisement") {
    val ads = List(
      new ImageAdvertisement("candy", 300),
      new ImageAdvertisement("paint", 300),
      new ImageAdvertisement("phone", 1024),
      new ImageAdvertisement("cookie", 300),
      new VideoAdvertisement("car", 310L),
      new VideoAdvertisement("bike", 150L)
    )
    val slots: Map[AdSlot, Int] = Map(ImageAdSlot(512, 0.03) -> 2, VideoAdSlot(300, 0.1) -> 2)
    val publisher = new AdPublisher(slots)
    val remamingAds = ads.map(x =>
      (x, publisher.remaining().filter(y => y._1.adType == x.adType && y._2 > 0))
    ).filter(x => {
      val (ad, slots) = x
      !slots.exists(slot => publisher.sell(slot._1, ad))
    }).map(_._1)

    val expectedReminingAds = List(2, 3, 4).map(ads)
    val expectedSales = List(5, 1, 0).map(ads)
    assert(remamingAds == expectedReminingAds)
    assert(publisher.currentSales.map(_.customer) == expectedSales)
  }

}
