const url = 'http://localhost:8080/api/card';

export async function findAll() {
  const response = await fetch(url);
  if (response.ok) {
    return await response.json();
  }
  return Promise.reject([response.status.toString()]);
}

export async function findById(cardId) {
  const response = await fetch(`${url}/${cardId}`);
  if (response.ok) {
    return await response.json();
  }
  return Promise.reject([response.status.toString()]);
}

// add
export async function add(card) {
  const init = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    },
    body: JSON.stringify(card),
  };

  const response = await fetch(url, init);
  if (response.ok) {
    return;
  }
  return Promise.reject([response.status.toString()]);
}

export async function update(card) {
  const init = {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(card),
  };

  const response = await fetch(`${url}/${card.cardId}`, init);
  if (response.ok) {
    return;
  }
  return Promise.reject([response.status.toString()]);
}

export async function updateCardType(card) {
  const init = {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(card),
  };

  const response = await fetch(`${url}/cardtype/${card.cardId}`, init);
  if (response.ok) {
    return;
  }
  return Promise.reject([response.status.toString()]);
}

export async function deleteById(cardId) {
  const config = {
    method: 'DELETE',
  };
  const response = await fetch(`${url}/${cardId}`, config);
  if (response.ok) {
    return;
  }
  return Promise.reject([response.status.toString()]);
}
