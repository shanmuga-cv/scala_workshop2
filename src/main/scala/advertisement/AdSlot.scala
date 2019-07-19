package advertisement

sealed abstract class AdSlot(val adType: String, val cost: Double)

case class ImageAdSlot(maxSizeInBytes: Long, override val cost: Double)
  extends AdSlot("IMAGE", cost)

case class VideoAdSlot(maxLengthInSeconds: Long, override val cost: Double)
  extends AdSlot("VIDEO", cost)
