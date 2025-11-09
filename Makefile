# =====================================
# Global config
# =====================================
MVN = mvn
JAVA = java

# Folder project
ADMIN_DIR = admin
PLAYER_DIR = player

# Nama hasil build
ADMIN_JAR = $(ADMIN_DIR)/target/quizmath_sma_admin-1.0-SNAPSHOT.jar
PLAYER_JAR = $(PLAYER_DIR)/target/quizmath_player-1.0-SNAPSHOT.jar

# =====================================
# Default target
# =====================================
.PHONY: all clean admin player

all:
	@echo "Usage:"
	@echo "  make admin   -> build & run Admin app"
	@echo "  make player  -> build & run Player app"
	@echo "  make clean   -> bersihin semua build"

# =====================================
# Build & Run Admin
# =====================================
admin:
	@echo ">>> Building Admin Project..."
	@cd $(ADMIN_DIR) && $(MVN) clean package -q
	@echo ">>> Running Admin..."
	@$(JAVA) -jar $(ADMIN_JAR)

# =====================================
# Build & Run Player
# =====================================
player:
	@echo ">>> Building Player Project..."
	@cd $(PLAYER_DIR) && $(MVN) clean package -q
	@echo ">>> Running Player..."
	@$(JAVA) -jar $(PLAYER_JAR)

# =====================================
# Clean semua target
# =====================================
clean:
	@echo ">>> Cleaning projects..."
	@cd $(ADMIN_DIR) && $(MVN) clean -q
	@cd $(PLAYER_DIR) && $(MVN) clean -q
	@echo ">>> Done cleaning."
