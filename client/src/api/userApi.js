const url = 'http://localhost:8080/api/user';

export async function findAll() {
  const response = await fetch(url);

  if (response.ok) {
    return await response.json();
  }

  return Promise.reject([response.status.toString()]);
}

export async function findById(id) {
  const response = await fetch(`${url}/${id}`);

  if (response.ok) {
    return await response.json();
  }

  return Promise.reject([response.status.toString()]);
}

export async function getUserEmailAddress(username) {
  const response = await fetch(`${url}/email/${username}`);

  if (response.ok) {
    return await response.json();
  }

  return Promise.reject([response.status.toString()]);
}

export async function authenticate(credentials) {
  const config = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(credentials),
  };

  const response = await fetch(`${url}/authenticate`, config);
  if (response.ok) {
    const json = await response.json();
    return json;
  }
  return Promise.reject([response.status.toString()]);
}
