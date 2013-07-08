assets
=======
Java/JOGL assets library


```java
Asset image = AssetManager.getAsset("my/path/to/myimage.png");

// com.jogamp.opengl.util.texture.Texture
// has internal cache with HashMap<String, Texture>
Texture tex = TextureManager.get("my/path/to/myimage.png"); // will call AssetManager internally

Sprite sprite = new Sprite(512,512,32,32);// init large 512x512 image map for 32x32 small images
SpriteRectangle texCoord1 = sprite.addImage("my/path/to/mysmallimage.png"); // 32x32
SpriteRectangle texCoord2 = sprite.addImage("my/path/to/mysmallimage2.png"); // 32x32
BufferedImage finalImage = sprite.getImage();
// ....
// same via TextureManager:
// @todo fix api

```