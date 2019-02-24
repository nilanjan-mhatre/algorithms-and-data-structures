
# coding: utf-8

# In[14]:


import chess


# In[20]:


board = chess.Board(None)


# In[21]:


print(board)


# In[22]:


board


# In[23]:


board.set_piece_at(chess.A1, chess.Piece.from_symbol("q"))


# In[24]:


board

