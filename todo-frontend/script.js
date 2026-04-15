const BASE_URL = "http://localhost:8787/todos";

let currentPage = 0;
let pageSize = 3;
let totalPages = 0;
let allTodos = [];

// ================= GET TODOS (PAGINATION) =================
async function getTodos(page = 0) {
  try {
    currentPage = page;

    const res = await fetch(
      `${BASE_URL}/getAllTodoInPage?page=${page}&size=${pageSize}`,
    );

    const data = await res.json();
    console.log("Pagination API Response:", data);

    const list = document.getElementById("todoList");
    list.innerHTML = "";

    // ✅ store current page todos
    allTodos = data.content;

    if (!data.content || data.content.length === 0) {
      list.innerHTML = "<li>No todos found</li>";
      updatePaginationInfo(0, 0);
      return;
    }

    data.content.forEach((todo) => {
      const li = document.createElement("li");

      li.innerHTML = `
        <span class="todo-text ${
          todo.status === "COMPLETED" ? "completed" : "pending"
        }">
          ${todo.title}
        </span>

        <div>
          <button onclick="markComplete(${todo.id})">✔</button>
          <button onclick="editTodo(${todo.id})">✏</button>
          <button onclick="deleteTodo(${todo.id})">❌</button>
        </div>
      `;

      list.appendChild(li);
    });

    totalPages = data.totalPages;
    updatePaginationInfo(data.number + 1, totalPages);
  } catch (error) {
    console.error("Error loading todos:", error);
  }
}

// ================= ADD TODO =================
async function addTodo() {
  const title = document.getElementById("title").value;

  if (!title.trim()) {
    alert("Please enter a todo");
    return;
  }

  await fetch(`${BASE_URL}/saveTodo`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      title: title,
      description: "Added from frontend",
      status: "PENDING",
    }),
  });

  document.getElementById("title").value = "";
  getTodos(currentPage);
}

// ================= DELETE TODO =================
async function deleteTodo(id) {
  await fetch(`${BASE_URL}/delete/${id}`, {
    method: "DELETE",
  });

  getTodos(currentPage);
}

// ================= MARK COMPLETE =================
async function markComplete(id) {
  await fetch(`${BASE_URL}/complete/${id}`, {
    method: "PATCH",
  });

  getTodos(currentPage);
}

// ================= EDIT TODO =================
function editTodo(id) {
  const todo = allTodos.find((t) => t.id === id);

  if (!todo) {
    alert("Todo not found");
    return;
  }

  const newTitle = prompt("Update Todo", todo.title);

  if (newTitle && newTitle.trim() !== "") {
    updateTodo(todo, newTitle);
  }
}

// ================= UPDATE TODO =================
async function updateTodo(todo, newTitle) {
  const res = await fetch(`${BASE_URL}/updateTodo/${todo.id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      id: todo.id,
      title: newTitle,
      description: todo.description,
      status: todo.status,
      createdAt: todo.createdAt,
    }),
  });

  console.log("Update Status:", res.status);

  getTodos(currentPage);
}

// ================= PAGINATION =================
function nextPage() {
  if (currentPage < totalPages - 1) {
    getTodos(currentPage + 1);
  }
}

function prevPage() {
  if (currentPage > 0) {
    getTodos(currentPage - 1);
  }
}

function updatePaginationInfo(current, total) {
  document.getElementById("pageInfo").innerText = `Page ${current} of ${total}`;
}

// ================= LOAD PAGE =================
window.onload = function () {
  getTodos(0);
};
