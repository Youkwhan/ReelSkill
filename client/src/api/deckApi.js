const url = 'http://localhost:8080/api/deck';

export async function findAll() {
  const response = await fetch(url);
  if (response.ok) {
    return await response.json();
  }
  return Promise.reject([response.status.toString()]);
}

export async function findById(deckId) {
  const response = await fetch(`${url}/${deckId}`);
  if (response.ok) {
    return await response.json();
  }
  return Promise.reject([response.status.toString()]);
}

// update and create functions
export async function save(deck) {
  let apiUrl = url;
  const init = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(deck),
  };

  if (deck.deckId) {
    init.method = 'PUT';
    apiUrl += `/${deck.deckId}`;
  }

  const response = await fetch(apiUrl, init);
  let errors;
  if (response.ok) {
    return;
  } else if (response.status === 400) {
    errors = await response.json();
  }

  return Promise.reject({ status: response.status, errors });
}

export async function deleteById(deckId) {
  const config = {
    method: 'DELETE',
  };
  const response = await fetch(`${url}/${deckId}`, config);
  if (response.ok) {
    return;
  }
  return Promise.reject([response.status.toString()]);
}
