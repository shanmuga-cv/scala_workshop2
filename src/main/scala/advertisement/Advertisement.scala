package advertisement

sealed abstract class Advertisement(val name: String, val adType: String)

class VideoAdvertisement(override val name: String, val lengthInSeconds: Long)
  extends Advertisement(name, "VIDEO")

class ImageAdvertisement(override val name: String, val sizeInBytes: Long)
  extends Advertisement(name, "IMAGE")


