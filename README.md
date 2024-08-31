# Video to Subtitle Generator

![GitHub repo size](https://img.shields.io/github/repo-size/yourusername/video-to-subtitle-generator)
![GitHub contributors](https://img.shields.io/github/contributors/yourusername/video-to-subtitle-generator)
![GitHub stars](https://img.shields.io/github/stars/yourusername/video-to-subtitle-generator?style=social)
![GitHub forks](https://img.shields.io/github/forks/yourusername/video-to-subtitle-generator?style=social)

A Java-based application that automates the process of generating subtitles from video files. This tool utilizes `FFmpeg` for audio extraction and `Whisper.cpp` for offline transcription, providing a complete solution for subtitle generation in various languages.

## Features

- **Automated Workflow**: Extracts audio from video files and generates subtitles in SRT format.
- **Offline Transcription**: Uses `Whisper.cpp` for offline, secure transcription.
- **Customizable**: Easily configurable paths and options for input videos and output subtitles.
- **Supports Various Formats**: Works with multiple video formats and generates universally compatible subtitle files.

## Getting Started

### Prerequisites

- **Java**: JDK 17 or later
- **FFmpeg**: Make sure `FFmpeg` is installed and accessible via the command line.
- **Whisper.cpp**: Ensure `Whisper.cpp` and required models are downloaded and compiled.
- **Git**: For version control and cloning this repository.

### Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/video-to-subtitle-generator.git
    cd video-to-subtitle-generator
    ```

2. **Install Dependencies**:  
   Make sure `FFmpeg` and `Whisper.cpp` are correctly set up in your system.

3. **Configure the Project**:
   - Set up paths in `SubtitleCreator.java` for `FFmpeg` and `Whisper.cpp`.
   - Place your video files in the `src/input` directory.

4. **Compile and Run**:
   - Use Gradle or your IDE to compile the project:
    ```bash
    ./gradlew build
    ./gradlew run
    ```

### Usage

1. **Add Video File**: Place the video file (e.g., `video.mp4`) in the `src/input` folder.
2. **Run the Application**: Execute the `SubtitleCreator` class to generate subtitles.
3. **Output**: The audio file (`audio.wav`) and subtitle file (`subtitles.srt`) will be created in the same folder as the input video.

### Example Commands

Convert video to audio using FFmpeg:

```bash
ffmpeg -i video.mp4 -ar 16000 -ac 1 -c:a pcm_s16le audio.wav
```

### Project Structure

```plaintext
video-to-subtitle-generator/
│
├── src/
│   ├── input/
│   │   └── video.mp4
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── learning/
│   │               ├── SubtitleCreator.java
│   │               ├── SubtitleGenerator.java
│   │               ├── VideoToAudioConverter.java
│   │               └── WhisperTranscription.java
│
├── output/
│   ├── audio.wav
│   └── subtitles.srt
│
├── README.md
└── build.gradle
```

### Java Classes Overview

- **SubtitleCreator.java**: Main class responsible for managing the subtitle creation workflow.
- **SubtitleGenerator.java**: Handles the logic for generating subtitles from the extracted audio.
- **VideoToAudioConverter.java**: Utilizes `FFmpeg` to convert video files to audio files.
- **WhisperTranscription.java**: Uses `Whisper.cpp` to transcribe audio into text.

### Build and Run with Gradle

```bash
./gradlew build
./gradlew run
```

### Dependencies

Make sure `FFmpeg` and `Whisper.cpp` are properly installed and configured in your system to ensure smooth execution of the subtitle generation process.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## Acknowledgements

- **FFmpeg**: A comprehensive multimedia framework used for handling video, audio, and other multimedia files and streams.
- **Whisper.cpp**: A transcription tool for offline transcription, providing a powerful solution for generating subtitles.
- **OpenAI**: For their AI models and contribution to the community.
- **Java Community**: For their continuous support and contribution to the Java ecosystem.
- Inspiration from the amazing projects and developers in the open-source community.
