# =======================================
# Makefile untuk Maven Project Java
# =======================================

APP_NAME = quizmath_sma_admin-1.0-SNAPSHOT.jar
TARGET_DIR = target
MAIN_CLASS = main.Main

# Default goal kalau cuma ketik "make"
.DEFAULT_GOAL := run

# =======================================
# Perintah utama
# =======================================

# Build project (tanpa test)
build:
	@echo "🧱 Building project..."
	mvn clean package -DskipTests

# Jalankan hasil build (.jar)
run: build
	@echo "🚀 Menjalankan aplikasi..."
	java -jar $(TARGET_DIR)/$(APP_NAME)

# Jalankan langsung via Maven (tanpa bikin .jar)
dev:
	@echo "⚙️  Development mode (exec:java)"
	mvn compile exec:java -Dexec.mainClass=$(MAIN_CLASS)

# Bersihkan hasil build
clean:
	@echo "🧹 Membersihkan project..."
	mvn clean

# Jalankan ulang: clean + build + run
rebuild:
	@echo "🔁 Rebuilding project..."
	mvn clean package -DskipTests
	java -jar $(TARGET_DIR)/$(APP_NAME)
