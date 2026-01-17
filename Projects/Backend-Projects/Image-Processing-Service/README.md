# Image Processing Service

A RESTful API service for uploading and processing images.

## Features

- Upload images
- Resize images
- Convert formats
- Apply filters
- Image compression
- Thumbnail generation

## API Endpoints

- `POST /api/images/upload` - Upload image
- `GET /api/images/{id}` - Get image
- `GET /api/images/{id}/thumbnail` - Get thumbnail
- `POST /api/images/{id}/resize` - Resize image
- `POST /api/images/{id}/convert` - Convert format
- `POST /api/images/{id}/filter` - Apply filter
- `DELETE /api/images/{id}` - Delete image

## Request Examples

### Upload Image
```
POST /api/images/upload
Content-Type: multipart/form-data

file: [image file]
```

### Resize Image
```json
POST /api/images/{id}/resize
{
  "width": 800,
  "height": 600,
  "maintainAspectRatio": true
}
```

### Convert Format
```json
POST /api/images/{id}/convert
{
  "format": "png"
}
```

## Supported Formats

- Input: JPEG, PNG, GIF, BMP, WebP
- Output: JPEG, PNG, WebP

## Image Operations

- Resize (with aspect ratio)
- Crop
- Rotate
- Flip (horizontal/vertical)
- Filters (grayscale, blur, sharpen)
- Watermark
- Compression

## Storage

- Local file system or cloud storage (S3)
- Image metadata in database
- Automatic cleanup of old images
