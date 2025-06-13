<template id="app-frame">
  <v-app>
    <!-- MENÚ LATERAL MINIMAL -->
    <div
      class="side-menu"
      @mouseenter="showFull = true"
      @mouseleave="showFull = false"
      :class="{ expanded: showFull }"
    >
      <div class="nav-container">
        <ul class="nav-list">
          <li @click="goTo('/')" :class="{ active: isActive('/') }">
            <span class="icon">🏠</span>
            <span class="label" v-show="showFull">Inicio</span>
          </li>
          <li @click="goTo('/desserts')" :class="{ active: isActive('/desserts') }">
            <span class="icon">🧁</span>
            <span class="label" v-show="showFull">Postres</span>
          </li>
          <li @click="goTo('/mouse')" :class="{ active: isActive('/mouse') }">
            <span class="icon">🐭</span>
            <span class="label" v-show="showFull">Ratones</span>
          </li>
          <li @click="goTo('/games')" :class="{ active: isActive('/games') }">
            <span class="icon">🎮</span>
            <span class="label" v-show="showFull">Videojuegos</span>
          </li>
          <li @click="goTo('/books')" :class="{ active: isActive('/books') }">
            <span class="icon">📚</span>
            <span class="label" v-show="showFull">Libros</span>
          </li>
        </ul>
      </div>
    </div>

    <!-- HEADER MINIMAL -->
    <div class="top-header" :class="{ expanded: showFull }">
      <div class="header-content">
        <div class="brand">
          <span class="brand-icon">✨</span>
          <span class="brand-text">Application by Chars</span>
        </div>
        <div class="header-actions">
          <v-btn icon variant="text" size="small">
            <v-icon>mdi-bell-outline</v-icon>
          </v-btn>
          <v-btn icon variant="text" size="small">
            <v-icon>mdi-account-circle-outline</v-icon>
          </v-btn>
        </div>
      </div>
    </div>

    <!-- CONTENIDO -->
    <div class="main-content" :class="{ expanded: showFull }">
      <slot></slot>
    </div>
  </v-app>
</template>

<script>
app.component("app-frame", {
  template: "#app-frame",
  data: () => ({
    showFull: false
  }),
  methods: {
    goTo(url) {
      window.location.href = url;
    },
    isActive(url) {
      return window.location.pathname === url;
    }
  }
});
</script>

<style>
/* Variables CSS */
:root {
  --sidebar-width: 56px;
  --sidebar-expanded-width: 240px;
  --header-height: 64px;
  --primary-color: #6366f1;
  --sidebar-bg: #ffffff;
  --sidebar-border: #e5e7eb;
  --text-primary: #1f2937;
  --text-secondary: #6b7280;
  --hover-bg: #f3f4f6;
  --active-bg: #eef2ff;
  --active-border: #6366f1;
  --transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Sidebar */
.side-menu {
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  width: var(--sidebar-width);
  background: var(--sidebar-bg);
  border-right: 1px solid var(--sidebar-border);
  transition: var(--transition);
  overflow: hidden;
  z-index: 100;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.side-menu.expanded {
  width: var(--sidebar-expanded-width);
}

.nav-container {
  padding: 20px 0;
}

.nav-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.nav-list li {
  position: relative;
  margin: 4px 8px;
  padding: 12px 16px;
  display: flex;
  align-items: center;
  cursor: pointer;
  border-radius: 12px;
  transition: var(--transition);
  color: var(--text-secondary);
}

.nav-list li:hover {
  background: var(--hover-bg);
  color: var(--text-primary);
  transform: translateX(2px);
}

.nav-list li.active {
  background: var(--active-bg);
  color: var(--active-border);
  border-left: 3px solid var(--active-border);
}

.nav-list li.active::before {
  content: '';
  position: absolute;
  right: 12px;
  top: 50%;
  transform: translateY(-50%);
  width: 6px;
  height: 6px;
  background: var(--active-border);
  border-radius: 50%;
  opacity: 0;
  animation: fadeIn 0.3s ease forwards;
}

@keyframes fadeIn {
  to { opacity: 1; }
}

.icon {
  font-size: 20px;
  width: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: var(--transition);
}

.label {
  margin-left: 16px;
  font-weight: 500;
  white-space: nowrap;
  opacity: 0;
  transform: translateX(-10px);
  transition: var(--transition);
}

.side-menu.expanded .label {
  opacity: 1;
  transform: translateX(0);
}

/* Header */
.top-header {
  position: fixed;
  top: 0;
  left: var(--sidebar-width);
  right: 0;
  height: var(--header-height);
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(10px);
  border-bottom: 1px solid var(--sidebar-border);
  transition: var(--transition);
  z-index: 50;
}

.top-header.expanded {
  left: var(--sidebar-expanded-width);
}

.header-content {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
}

.brand {
  display: flex;
  align-items: center;
  gap: 12px;
}

.brand-icon {
  font-size: 24px;
  animation: rotate 3s linear infinite;
}

@keyframes rotate {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

.brand-text {
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
  background: linear-gradient(135deg, var(--primary-color), #8b5cf6);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 8px;
}

/* Main Content */
.main-content {
  margin-top: var(--header-height);
  margin-left: var(--sidebar-width);
  padding: 24px;
  min-height: calc(100vh - var(--header-height));
  transition: var(--transition);
  background: #fafafa;
}

.main-content.expanded {
  margin-left: var(--sidebar-expanded-width);
}

/* Scrollbar personalizada */
::-webkit-scrollbar {
  width: 6px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}

/* Responsive */
@media (max-width: 768px) {
  .side-menu {
    transform: translateX(-100%);
  }

  .side-menu.expanded {
    transform: translateX(0);
    width: 280px;
  }

  .top-header, .main-content {
    left: 0;
    margin-left: 0;
  }
}
</style>