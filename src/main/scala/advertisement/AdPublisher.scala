package advertisement

import store.{Sale, Store}

class AdPublisher(override val capacity: Map[AdSlot, Int]) extends Store [AdSlot, Advertisement]{
  private var sales: List[Sale[AdSlot, Advertisement]] = List()

  override def currentSales: List[Sale[AdSlot, Advertisement]] = sales

  override def sell(slot: AdSlot, ad: Advertisement): Boolean = {
    (slot, ad) match {
      case (videoSlot: VideoAdSlot, videoAd: VideoAdvertisement)
        if videoAd.lengthInSeconds <= videoSlot.maxLengthInSeconds && remaining().getOrElse(videoSlot, 0) > 0 =>
          sales = Sale(s"vid-${sales.length}", videoSlot, videoAd) +: sales
          true
      case (imageSlot: ImageAdSlot, imageAd: ImageAdvertisement)
        if imageAd.sizeInBytes <= imageSlot.maxSizeInBytes && remaining().getOrElse(imageSlot, 0) > 0  =>
          sales = Sale(s"img-${sales.length}", imageSlot, imageAd) +: sales
          true
      case _ => false
    }
  }
}
