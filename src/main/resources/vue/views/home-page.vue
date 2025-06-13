<template id="home-page">
  <app-frame>
    <v-container>
      <v-row>
        <!-- Card existente -->
        <v-col cols="12" md="6">
          <v-card
            :disabled="loading"
            :loading="loading"
            class="mx-auto my-12"
            max-width="374"
          >
            <template v-slot:loader="{ isActive }">
              <v-progress-linear
                :active="isActive"
                color="deep-purple"
                height="4"
                indeterminate
              ></v-progress-linear>
            </template>

            <v-img
              height="250"
              src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
              cover
            ></v-img>

            <v-card-item>
              <v-card-title>Cafe Badilico</v-card-title>

              <v-card-subtitle>
                <span class="me-1">Local Favorite</span>

                <v-icon
                  color="error"
                  icon="mdi-fire-circle"
                  size="small"
                ></v-icon>
              </v-card-subtitle>
            </v-card-item>

            <v-card-text>
              <v-row
                align="center"
                class="mx-0"
              >
                <v-rating
                  :model-value="4.5"
                  color="amber"
                  density="compact"
                  size="small"
                  half-increments
                  readonly
                ></v-rating>

                <div class="text-grey ms-4">
                  4.5 (413)
                </div>
              </v-row>

              <div class="my-4 text-subtitle-1">
                $ • Italian, Cafe
              </div>

              <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.</div>
            </v-card-text>

            <v-divider class="mx-4 mb-1"></v-divider>

            <v-card-title>Tonight's availability</v-card-title>

            <div class="px-4 mb-2">
              <v-chip-group v-model="selection" selected-class="bg-deep-purple-lighten-2">
                <v-chip>5:30PM</v-chip>
                <v-chip>7:30PM</v-chip>
                <v-chip>8:00PM</v-chip>
                <v-chip>9:00PM</v-chip>
              </v-chip-group>
            </div>

            <v-card-actions>
              <v-btn
                color="deep-purple-lighten-2"
                text="Reserve"
                block
                border
                @click="reserve"
              ></v-btn>
            </v-card-actions>
          </v-card>
        </v-col>

        <!-- Nueva tabla de estadísticas -->
        <v-col cols="12" md="6">
          <v-card class="mx-auto my-12" max-width="500">
            <v-card-title class="d-flex align-center">
              <v-icon color="primary" icon="mdi-chart-line" class="me-2"></v-icon>
              Dashboard Statistics
            </v-card-title>

            <v-card-text>
              <v-data-table
                :headers="statsHeaders"
                :items="statistics"
                :hide-default-footer="true"
                class="elevation-1"
                density="comfortable"
              >
                <template v-slot:item.icon="{ item }">
                  <v-icon :color="item.color" :icon="item.icon" size="small"></v-icon>
                </template>

                <template v-slot:item.value="{ item }">
                  <v-chip
                    :color="item.color"
                    size="small"
                    variant="tonal"
                    class="font-weight-bold"
                  >
                    {{ item.value }}
                  </v-chip>
                </template>

                <template v-slot:item.trend="{ item }">
                  <v-icon
                    :color="item.trend > 0 ? 'success' : 'error'"
                    :icon="item.trend > 0 ? 'mdi-trending-up' : 'mdi-trending-down'"
                    size="small"
                  ></v-icon>
                  <span :class="item.trend > 0 ? 'text-success' : 'text-error'" class="text-caption ms-1">
                    {{ Math.abs(item.trend) }}%
                  </span>
                </template>
              </v-data-table>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-btn
                color="primary"
                variant="text"
                prepend-icon="mdi-refresh"
                @click="refreshStats"
              >
                Refresh
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn
                color="secondary"
                variant="outlined"
                size="small"
                @click="showDetails = !showDetails"
              >
                {{ showDetails ? 'Hide' : 'Show' }} Details
              </v-btn>
            </v-card-actions>

            <v-expand-transition>
              <div v-if="showDetails">
                <v-divider></v-divider>
                <v-card-text>
                  <v-alert
                    color="info"
                    icon="mdi-information"
                    variant="tonal"
                    class="text-caption"
                  >
                    Statistics are updated in real-time and show the current performance metrics of your application.
                  </v-alert>
                </v-card-text>
              </div>
            </v-expand-transition>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </app-frame>
</template>

<script>
app.component("home-page", {
  template: "#home-page",
  setup() {
    const { ref } = Vue;

    const loading = ref(false);
    const selection = ref(1);
    const showDetails = ref(false);

    const statsHeaders = [
      { title: '', key: 'icon', align: 'center', width: '50px', sortable: false },
      { title: 'Metric', key: 'name', align: 'start' },
      { title: 'Value', key: 'value', align: 'center' },
      { title: 'Trend', key: 'trend', align: 'center', sortable: false },
    ];

    const statistics = ref([
      {
        name: 'Active Users',
        value: '1,234',
        trend: 12.5,
        icon: 'mdi-account-multiple',
        color: 'primary'
      },
      {
        name: 'Total Sales',
        value: '$45,678',
        trend: 8.3,
        icon: 'mdi-currency-usd',
        color: 'success'
      },
      {
        name: 'Page Views',
        value: '89,012',
        trend: -2.1,
        icon: 'mdi-eye',
        color: 'info'
      },
      {
        name: 'Conversion Rate',
        value: '3.45%',
        trend: 5.7,
        icon: 'mdi-chart-line',
        color: 'warning'
      },
      {
        name: 'Bounce Rate',
        value: '32.1%',
        trend: -4.2,
        icon: 'mdi-exit-to-app',
        color: 'error'
      },
    ]);

    function reserve() {
      loading.value = true;
      setTimeout(() => (loading.value = false), 2000);
    }

    function refreshStats() {
      // Simulate refreshing stats with random changes
      statistics.value.forEach(stat => {
        const change = (Math.random() - 0.5) * 20; // Random change between -10 and +10
        stat.trend = Math.round(change * 10) / 10;
      });
    }

    return {
      loading,
      selection,
      showDetails,
      statsHeaders,
      statistics,
      reserve,
      refreshStats
    };
  }
});
</script>