#include <stdint.h>
#include <gdk-pixbuf/gdk-pixbuf.h>

/// Opens and reads an image from file.
/// @return The pointer to the image.
/// @param[in] imageFileName File's name to load from.
/// @param[in] w Width of the image.
/// @param[in] h Height of the image.
uint32_t* LoadImage(char* imageFileName, int* w, int* h);

/// Saves an image to file.
/// @param[in] imageFileName File's name to save to, please save images to .png format.
/// @param[in] pix The pointer to the image.
/// @param[in] w Width of the image.
/// @param[in] h Height of the image.
void SaveImage(char* imageFileName, uint32_t* pix, int w, int h);
