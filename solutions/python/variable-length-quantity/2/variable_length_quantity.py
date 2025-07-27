# Mask to extract the 7 bits of the number in each byte
vlq_num_mask = 0b1111111

# Mask to check the continuation bit (the 8th bit, bit 7)
vlq_continue_mask = 1 << 7

# Function to encode a list of numbers into VLQ format
def encode(numbers):
    encoded_bytes = []

    # Loop through each number in the list
    for number in numbers:
        # For each number, break it into chunks of 7 bits (each chunk will become one byte)
        # Determine how many chunks are needed by calculating the bit length of the number
        for i in range(((number >> 1).bit_length()) // 7, -1, -1):
            # Get the current 7 bits from the number by shifting and masking
            current_byte = (number >> (7 * i)) & vlq_num_mask
            
            # Set the continuation bit (bit 7) to 1 if it's not the last byte
            if i > 0:
                current_byte |= vlq_continue_mask
            
            # Append the current byte to the encoded list
            encoded_bytes.append(current_byte)
    
    return encoded_bytes


# Function to decode a list of VLQ encoded bytes back into numbers
def decode(bytes_):
    return list(_decode(bytes_))


# Helper function to decode the VLQ bytes into numbers
def _decode(bytes_):
    code = 0  # Holds the decoded number while processing the bytes
    
    # Check if the last byte has the continuation bit set, which would indicate an incomplete sequence
    if bytes_[-1] & vlq_continue_mask:
        raise ValueError('Incomplete sequence')  # Error if the sequence is incomplete
    
    # Loop through each byte in the list of bytes
    for byte in bytes_:
        # Shift the current code to the left by 7 bits and add the 7 bits from the current byte
        code = (code << 7) | (byte & vlq_num_mask)

        # If the current byte does not have the continuation bit (bit 7 is 0), we have reached the end of a number
        if not (byte & vlq_continue_mask):
            yield code  # Yield the decoded number
            code = 0  # Reset the code to start decoding the next number

