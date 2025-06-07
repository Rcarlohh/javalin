<template id="app-frame">
  <v-app>
    <!-- MENÚ LATERAL -->
    <div
      class="side-menu"
      @mouseenter="showFull = true"
      @mouseleave="showFull = false"
      :class="{ expanded: showFull }"
    >
      <ul class="nav-list">
        <li @click="goTo('/')">
          <span class="icon">🏠</span>
          <span class="label" v-show="showFull">Inicio</span>
        </li>
        <li @click="goTo('/desserts')">
          <span class="icon">🧁</span>
          <span class="label" v-show="showFull">Postres</span>
        </li>
        <li @click="goTo('/mouse')">
          <span class="icon">🐭</span>
          <span class="label" v-show="showFull">Ratones</span>
        </li>
        <li @click="goTo('/games')">
          <span class="icon">🎮</span>
          <span class="label" v-show="showFull">Videojuegos</span>
        </li>
        <li @click="goTo('/books')">
          <span class="icon">📚</span>
          <span class="label" v-show="showFull">Libros</span>
        </li>
      </ul>
    </div>

    <!-- BARRA SUPERIOR -->
    <div class="top-bar">
      <button @click="drawer = !drawer" class="toggle-btn">☰</button>
      <span class="title">Application by Chars</span>
    </div>

    <!-- CONTENIDO -->
    <div class="main-content">
      <slot></slot>
    </div>
  </v-app>
</template>

<script>
app.component("app-frame", {
  template: "#app-frame",
  data: () => ({
    drawer: true,
    showFull: false
  }),
  methods: {
    goTo(url) {
      window.location.href = url;
    }
  }
});
</script>

<style>
/* Layout básico */
.side-menu {
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  width: 60px;
  background-color: #2c3e50;
  color: white;
  transition: width 0.3s ease;
  overflow-x: hidden;
  z-index: 10;
}
.side-menu.expanded {
  width: 180px;
}

.nav-list {
  list-style: none;
  padding: 0;
  margin: 60px 0 0;
}
.nav-list li {
  padding: 15px 10px;
  display: flex;
  align-items: center;
  cursor: pointer;
}
.nav-list li:hover {
  background-color: #34495e;
}
.icon {
  font-size: 20px;
  width: 30px;
  text-align: center;
}
.label {
  margin-left: 10px;
  white-space: nowrap;
}

/* Top Bar */
.top-bar {
  height: 50px;
  background-color: #3498db;
  color: white;
  display: flex;
  align-items: center;
  padding: 0 15px;
  margin-left: 60px;
}
.toggle-btn {
  background: none;
  border: none;
  font-size: 22px;
  color: white;
  margin-right: 10px;
}
.title {
  font-size: 18px;
}

/* Main Content */
.main-content {
  margin-left: 60px;
  padding: 20px;
}
.side-menu.expanded + .top-bar,
.side-menu.expanded + .top-bar + .main-content {
  margin-left: 180px;
  transition: margin-left 0.3s ease;
}
</style>
