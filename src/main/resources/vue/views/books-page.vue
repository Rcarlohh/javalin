<template id="books-page">
  <app-frame>
    <v-sheet border rounded class="ma-4 pa-2">
      <v-data-table
        :headers="headers"
        :hide-default-footer="books.length < 11"
        :items="books"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>
              <v-icon color="medium-emphasis" icon="mdi-book-multiple" size="x-small" start></v-icon>
              Popular books
            </v-toolbar-title>

            <v-btn
              class="me-2"
              prepend-icon="mdi-plus"
              rounded="lg"
              text="Add a Book"
              border
              @click="add"
            ></v-btn>
          </v-toolbar>
        </template>

        <template v-slot:item.title="{ value }">
          <v-chip :text="value" border="thin opacity-25" prepend-icon="mdi-book" label>
            <template v-slot:prepend>
              <v-icon color="medium-emphasis"></v-icon>
            </template>
          </v-chip>
        </template>

        <template v-slot:item.actions="{ item }">
          <div class="d-flex ga-2 justify-end">
            <v-icon color="medium-emphasis" icon="mdi-pencil" size="small" @click="edit(item.id)"></v-icon>
            <v-icon color="medium-emphasis" icon="mdi-delete" size="small" @click="remove(item.id)"></v-icon>
          </div>
        </template>

        <template v-slot:no-data>
          <v-btn
            prepend-icon="mdi-backup-restore"
            rounded="lg"
            text="Reset data"
            variant="text"
            border
            @click="reset"
          ></v-btn>
        </template>
      </v-data-table>
    </v-sheet>

    <v-dialog v-model="dialog" max-width="500">
      <v-card
        :subtitle="`${isEditing ? 'Update' : 'Create'} your favorite book`"
        :title="`${isEditing ? 'Edit' : 'Add'} a Book`"
      >
        <template v-slot:text>
          <v-row>
            <v-col cols="12">
              <v-text-field v-model="record.title" label="Title"></v-text-field>
            </v-col>

            <v-col cols="12" md="6">
              <v-text-field v-model="record.author" label="Author"></v-text-field>
            </v-col>

            <v-col cols="12" md="6">
              <v-select
                v-model="record.genre"
                :items="['Fiction', 'Dystopian', 'Non-Fiction', 'Sci-Fi']"
                label="Genre"
              ></v-select>
            </v-col>

            <v-col cols="12" md="6">
              <v-text-field
                v-model="record.year"
                type="number"
                :max="adapter.getYear(adapter.date())"
                :min="1"
                label="Year"
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6">
              <v-text-field
                v-model="record.pages"
                type="number"
                :min="1"
                label="Pages"
              ></v-text-field>
            </v-col>
          </v-row>
        </template>

        <v-divider></v-divider>

        <v-card-actions class="bg-surface-light">
          <v-btn text="Cancel" variant="plain" @click="dialog = false"></v-btn>
          <v-spacer></v-spacer>
          <v-btn text="Save" @click="save"></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </app-frame>
</template>

<script>
app.component("books-page", {
  template: "#books-page",
  setup() {
    const { ref, shallowRef, onMounted } = Vue;
    const adapter = Vuetify.useDate();

    const DEFAULT_RECORD = {
      title: '', author: '', genre: '',
      year: adapter.getYear(adapter.date()),
      pages: 1
    };

    const books = ref([]);
    const record = ref(DEFAULT_RECORD);
    const dialog = shallowRef(false);
    const isEditing = shallowRef(false);

    const headers = [
      { title: 'Title', key: 'title', align: 'start' },
      { title: 'Author', key: 'author' },
      { title: 'Genre', key: 'genre' },
      { title: 'Year', key: 'year', align: 'end' },
      { title: 'Pages', key: 'pages', align: 'end' },
      { title: 'Actions', key: 'actions', align: 'end', sortable: false },
    ];

    onMounted(() => {
      reset();
    });

    function add() {
      isEditing.value = false;
      record.value = { ...DEFAULT_RECORD };
      dialog.value = true;
    }

    function edit(id) {
      isEditing.value = true;
      const found = books.value.find(b => b.id === id);
      record.value = { ...found };
      dialog.value = true;
    }

    function remove(id) {
      const index = books.value.findIndex(b => b.id === id);
      books.value.splice(index, 1);
    }

    function save() {
      if (isEditing.value) {
        const index = books.value.findIndex(b => b.id === record.value.id);
        books.value[index] = { ...record.value };
      } else {
        record.value.id = books.value.length + 1;
        books.value.push({ ...record.value });
      }
      dialog.value = false;
    }

    function reset() {
      dialog.value = false;
      record.value = { ...DEFAULT_RECORD };
      books.value = [
        { id: 1, title: 'To Kill a Mockingbird', author: 'Harper Lee', genre: 'Fiction', year: 1960, pages: 281 },
        { id: 2, title: '1984', author: 'George Orwell', genre: 'Dystopian', year: 1949, pages: 328 },
        { id: 3, title: 'The Great Gatsby', author: 'F. Scott Fitzgerald', genre: 'Fiction', year: 1925, pages: 180 },
        { id: 4, title: 'Sapiens', author: 'Yuval Noah Harari', genre: 'Non-Fiction', year: 2011, pages: 443 },
        { id: 5, title: 'Dune', author: 'Frank Herbert', genre: 'Sci-Fi', year: 1965, pages: 412 },
      ];
    }

    return {
      adapter, books, record, dialog, isEditing,
      headers, add, edit, remove, save, reset
    };
  }
});
</script>