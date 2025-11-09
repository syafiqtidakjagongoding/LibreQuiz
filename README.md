# 🧠 LibreQuiz

**LibreQuiz** is a native desktop quiz platform designed to promote honesty and prevent cheating during quizzes or exams.  
Built with **Java (Swing)** and **FlatLaf**, it provides a modern, cross-platform UI experience optimized for **Linux** systems.

---

## ✨ Key Features

- 🔒 **Anti-cheat protection** — keyboard locking and restricted shortcuts prevent switching windows or copying answers.
- 🧠 **Interactive quiz interface** — designed for students and classrooms.
- 🧩 **Modular design** — includes separate Admin and Player applications.
- 💾 **MySQL database integration** — secure storage for questions, answers, and results.
- 🖥️ **Native Linux support** — tested and optimized for major Linux desktop environments (GNOME, LXQt, XFCE, etc).
- 🎨 **Modern UI** — FlatLaf provides a clean, native look consistent with current desktop themes.

---

## 🏗️ Project Structure

```
LibreQuiz/
├── admin/ # Admin interface for managing questions, sessions, and users
│ ├── src/
│ └── pom.xml
├── player/ # Player (student) interface for taking quizzes
│ ├── src/
│ └── pom.xml
└── Makefile # Simple unified build & run system for both projects
```

## 🧩 Why mix Java + C?

Even though Java is portable and powerful, there are times when you want low-level system control — especially for your case (anti-cheat / keyboard locking).

You might need features that Java alone can’t access directly, such as:

Intercepting keyboard and mouse events globally

Blocking Alt+Tab, Ctrl+Alt+T, or Super (Windows) key

Talking to Linux input devices (/dev/input/event*)

Using X11 or Wayland APIs for window focus control

Java doesn’t expose these natively, but C (or C++) does — so you can use JNI (Java Native Interface) to bridge between them.


---

## ⚙️ Requirements

- **Java 21** or higher  
- **Apache Maven 3.9+**  
- **MySQL** (for backend storage)

---

## 🚀 Build & Run

LibreQuiz is split into two Maven-based subprojects — `admin` and `player`.  
You can build and run each using the included **Makefile**:

### Run the Admin App
```bash
make admin
```

### Run the Player App
```
make player
```

### Clean all builds
```
make clean
```

Both apps will automatically package into standalone fat JARs using the maven-shade-plugin
.
No extra dependencies or setup required — just java -jar.

🧰 Technologies Used
Category	Technology
Language	Java 21
Build System	Apache Maven
UI Framework	Swing + FlatLaf
Database	MySQL
Packaging	Maven Shade Plugin
OS Target	Linux (tested on Ubuntu, Arch, Fedora)

🧱 Architecture Overview

Admin App
Allows teachers to create, edit, and manage quiz content and participants.

Player App
Provides a restricted environment for students to take quizzes — disables window switching and certain keyboard shortcuts to ensure fair play.

🧑‍💻 Contributing

Contributions and feature suggestions are welcome!
Feel free to open an issue or submit a pull request on GitHub.

