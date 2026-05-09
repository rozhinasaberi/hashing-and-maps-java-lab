import streamlit as st


def bucketize(keys: list[int], bucket_count: int) -> list[list[int]]:
    buckets = [[] for _ in range(bucket_count)]
    for key in keys:
        buckets[key % bucket_count].append(key)
    return buckets


st.set_page_config(page_title="Hashing and Maps Tutor", page_icon="🗂️", layout="wide")

st.title("🗂️ Hashing and Maps Tutor")
st.write(
    "This tutor app explains the hashing and map ideas from Lab 6 by showing how keys distribute across buckets and how load factor affects collisions."
)

raw = st.text_input("Enter integer keys", "12, 44, 13, 88, 23, 94, 11")
bucket_count = st.slider("Number of buckets", 3, 15, 7)
keys = [int(x.strip()) for x in raw.split(",") if x.strip()]
buckets = bucketize(keys, bucket_count)

col1, col2 = st.columns(2)
col1.metric("Keys stored", len(keys))
col2.metric("Load factor", f"{len(keys) / bucket_count:.2f}")

for i, bucket in enumerate(buckets):
    st.write(f"Bucket {i}: {bucket}")

st.info(
    "A lower load factor usually means fewer collisions and faster lookup, while a higher load factor saves space but can slow access."
)
